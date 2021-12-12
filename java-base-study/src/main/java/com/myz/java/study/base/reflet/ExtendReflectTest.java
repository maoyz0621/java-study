/**
 * Copyright 2021 Inc.
 **/
package com.myz.java.study.base.reflet;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author maoyz0621 on 2021/10/31
 * @version v1.0
 */
public class ExtendReflectTest {

    @Test
    public void testField() {
        Field[] allFields = FieldUtils.getAllFields(HistoryBook.class);
        for (Field field : allFields) {
            // private java.lang.String com.myz.java.study.base.reflet.HistoryBook.author
            // private java.lang.String com.myz.java.study.base.reflet.Book.title
            // private double com.myz.java.study.base.reflet.Book.price
            System.out.println(field);
        }
    }

    @Test
    public void testMethod() {
        Class<HistoryBook> class1 = HistoryBook.class;
        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("==============================================");

        // 公共方法
        Method[] declaredMethods = class1.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }
}