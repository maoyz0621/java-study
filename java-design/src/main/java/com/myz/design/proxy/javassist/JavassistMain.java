/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-14 17:09  Inc. All rights reserved.
 */
package com.myz.design.proxy.javassist;

import javassist.*;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;


import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 参考1: https://cloud.tencent.com/developer/article/1489861
 * 参考2: https://www.cnblogs.com/rickiyang/p/11336268.html
 * <p>
 * 官方文档： http://www.javassist.org/tutorial/tutorial.html
 *
 * @author maoyz
 */
public class JavassistMain {

    public static void main(String[] args) throws Exception {
        JavassistMain main = new JavassistMain();
        // main.build( ClassPool.getDefault(), true);

        main.create();
        // main.run();
    }

    public void execute() throws Exception {
        //加载clazz类，并创建对象
        Class<?> c = Class.forName("com.myz.design.proxy.javassist.JavassistTest");
        Object o = c.newInstance();
        //调用execute()方法
        Method method = o.getClass().getMethod("execute", new Class[]{});
        method.invoke(o, new Object[]{});
    }

    public void build(ClassPool classPool, boolean needSave) throws Exception {
        // 1 创建ClassPool
        if (classPool == null) {
            classPool = ClassPool.getDefault();
        }

        // 2 生成JavassistTest
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        CtClass ctClass = classPool.makeClass("com.myz.design.proxy.javassist.JavassistTest");

        StringBuffer body = null;
        // 创建字段，指定了字段类型、字段名称、字段所属的类
        CtField field = new CtField(classPool.get("java.lang.String"), "prop", ctClass);
        // 指定该字段使用private修饰
        field.setModifiers(Modifier.PUBLIC);
        // 设置prop字段的getter/setter方法
        ctClass.addMethod(CtNewMethod.getter("getProp", field));
        ctClass.addMethod(CtNewMethod.setter("setProp", field));

        // 设置prop字段的初始化值，并将prop字段添加到clazz中
        ctClass.addField(field, CtField.Initializer.constant("MyName"));


        // 创建无参构造方法，指定了构造方法的参数类型和构造方法所属的类
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        // 设置方法体
        body = new StringBuffer();
        body.append("{\n prop=\"MyName\";\n}");
        ctConstructor.setBody(body.toString());
        // 将构造方法添加到clazz中
        ctClass.addConstructor(ctConstructor);

        // 有参数构造方法
        ctConstructor = new CtConstructor(new CtClass[]{classPool.get("java.lang.String")}, ctClass);
        // $0=this / $1,$2,$3... 代表方法参数
        ctConstructor.setBody("{$0.prop = $1;}");
        ctClass.addConstructor(ctConstructor);


        // 创建execute()方法，指定了方法的返回值、方法名称、方法参数列表以及方法所属的类
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, ctClass);
        // 指定方法使用public修饰
        ctMethod.setModifiers(Modifier.PUBLIC);
        //设置方法体
        body = new StringBuffer();
        body.append("{\n System.out.println(\"execute():\" + this.prop);");
        body.append("\n}");
        ctMethod.setBody(body.toString());
        // 将execute()方法添加到clazz中
        ctClass.addMethod(ctMethod);

        //将上面定义的JavassistTest类保存到指定的目录
        if (needSave) {
            ctClass.writeFile(path);
        }

    }

    public void create() throws Exception {
        // 1 创建ClassPool
        ClassPool classPool = ClassPool.getDefault();

        // 2 生成JavassistTest
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        CtClass ctClass = null;
        try {
            ctClass = classPool.get("com.myz.design.proxy.javassist.JavassistTest");
        } catch (NotFoundException e) {
            build(classPool, false);
            ctClass = classPool.get("com.myz.design.proxy.javassist.JavassistTest");
        }
        if (ctClass == null) {
            return;
        }

        if (ctClass.isFrozen()) {
            ctClass.freeze();
        }
        //创建字段，指定了字段类型、字段名称、字段所属的类
        CtField field = new CtField(classPool.get("java.lang.Integer"), "age", ctClass);

        //指定该字段使用private修饰
        field.setModifiers(Modifier.PRIVATE);
        // 包含父类所有方法
        CtMethod[] methods = ctClass.getMethods();

        // 本身方法
        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        for (CtMethod declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            System.out.println(name);
        }
        //设置age字段的getter/setter方法
        ctClass.addMethod(CtNewMethod.setter("getAge", field));
        ctClass.addMethod(CtNewMethod.getter("setAge", field));

        //将age字段添加到clazz中
        ctClass.addField(field);

        // 将上面定义的JavassistTest类保存到指定的目录
        ctClass.writeFile(path);
    }

    public void run() throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定父类，ProxyFactory会动态生成继承该父类的子类
        // 因为没有.java源文件，此处会飘红，但有.class文件可以执行
        // proxyFactory.setSuperclass(com.myz.design.proxy.javassist.JavassistTest.class);

        // 设置其接口
        // proxyFactory.setInterfaces();

        // 设置过滤器，判断哪些方法调用需要被拦截
        proxyFactory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                return "".equals(m.getName());
            }
        });

        Class<?> clazz = proxyFactory.createClass();

        // 设置拦截处理
        MethodHandler handler = new MethodHandler() {

            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                System.out.println(thisMethod.getName());
                System.out.println("前置处理");
                Object result = proceed.invoke(self, args);
                System.out.println("后置处理:" + result);
                return result;
            }
        };

        // JavassistTest javassistTest = (JavassistTest) clazz.newInstance();
        // ((Proxy) javassistTest).setHandler(handler);

    }
}