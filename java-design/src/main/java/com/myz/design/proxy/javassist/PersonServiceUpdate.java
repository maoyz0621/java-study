/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-14 17:09  Inc. All rights reserved.
 */
package com.myz.design.proxy.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.lang.reflect.Method;

/**
 * 修改现有的类对象
 * https://www.cnblogs.com/sunfie/p/5154246.html
 *
 * @author maoyz
 */
public class PersonServiceUpdate {

    public static void main(String[] args) {
        try {
            new PersonServiceUpdate().update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.myz.design.proxy.javassist.PersonService");

        CtMethod personFly = cc.getDeclaredMethod("personFly");
        personFly.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
        personFly.insertAfter("System.out.println(\"成功落地。。。。\");");

        // 新增一个方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
        cc.addMethod(ctMethod);

        // 实例化
        Object person = cc.toClass().newInstance();
        // 调用 personFly 方法
        Method personFlyMethod = person.getClass().getMethod("personFly");
        personFlyMethod.invoke(person);

        //调用 joinFriend 方法
        Method execute = person.getClass().getMethod("joinFriend");

        // 执行方法
        execute.invoke(person);
    }

}