package com.myz.java.study.base.reflet;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/7 16:47
 */
public class ReflectTest {

    @Test
    public void test() throws Exception {
        Field title0 = FieldUtils.getField(Book.class, "title");
        // null
        System.out.println(title0);

        Field title = FieldUtils.getField(Book.class, "title", true);
        // private java.lang.String com.myz.java.study.base.reflet.Book.title
        System.out.println(title);
    }

    /**
     * Declared 公共的
     */
    @Test
    public void testDeclaredField() throws Exception {
        Field title0 = FieldUtils.getDeclaredField(Book.class, "title");
        // null
        System.out.println(title0);

        Field title = FieldUtils.getDeclaredField(Book.class, "title", true);
        // private java.lang.String com.myz.java.study.base.reflet.Book.title
        System.out.println(title);
    }

    @Test
    public void testAllFields() throws Exception {
        Field[] field0 = FieldUtils.getAllFields(Book.class);
        // private java.lang.String com.myz.java.study.base.reflet.Book.title
        // private double com.myz.java.study.base.reflet.Book.price
        for (Field field : field0) {
            System.out.println(field);
            AnnotatedType annotatedType = field.getAnnotatedType();
        }
    }

    @Test
    public void testMethod() throws Exception {
        Book book = new Book();
        Method method = MethodUtils.getAccessibleMethod(Book.class, "mail", int.class, int.class);
        // public void com.myz.java.study.base.reflet.Book.mail(int,int)
        System.out.println(method);


        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            // 入参类型
            System.out.println(parameter);
            System.out.println(parameter.getType());
        }

        Class<?> returnType = method.getReturnType();
        System.out.println("返回类型：" + returnType);

        // 反射调用方法
        method.setAccessible(true);
        method.invoke(book, 2, 5);


        // 反射调用方法
        MethodUtils.invokeMethod(book, "mail", 12, 5);
    }

}
