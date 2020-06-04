package com.myz.java.study.annotation;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

@MyAnnotation(value = {"a", "b"}, age = 21)
public class Main {

    public static void main(String[] args) {

        System.out.println(StringUtils.contains("abc", "ab"));
        Collection<Integer> col = CollectionUtils.union(new ArrayList<>(), new ArrayList<>());
        System.out.println(col);
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        MyAnnotation myAnnotation = Main.class.getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation.age());
        System.out.println(myAnnotation.value()[0]);
        System.out.println("---------------");

        /*
         * 解析注解
         */


        try {
            //1 反射类
            Class clz = Class.forName("com.myz.java.study.annotation.User");
            // 2 判断注解类是不是当前类的注解
            boolean flag = clz.isAnnotationPresent(MyAnnotation.class);
            if (flag) {
                //3 获取注解
                MyAnnotation myAnnotation1 = (MyAnnotation) clz.getAnnotation(MyAnnotation.class);
                System.out.println(myAnnotation1);
            }
            // 4 获取方法上的注解
            Method[] methods = clz.getMethods();
            //　遍历方法
            for (Method m : methods) {
                // 判断方法上是否有注解
                if (m.isAnnotationPresent(MyAnnotation.class)) {
                    MyAnnotation myAnnotation1 = m.getAnnotation(MyAnnotation.class);
                    System.out.println(myAnnotation1);
                }
            }

            System.out.println("----------------");

            for (Method m1 : methods) {
                //　遍历出所有的注解
                Annotation[] annotations = m1.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof MyAnnotation) {
                        MyAnnotation myAnnotation1 = (MyAnnotation) annotation;
                        System.out.println(myAnnotation1);
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}