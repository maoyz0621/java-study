/*
 * Copyright (C) 2019, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-09 17:12  Inc. All rights reserved.
 */
package com.myz.java.study.base.reference;

import java.io.Serializable;

/**
 * 重载
 * 按照char>int>long>float>double的顺序转型进行匹配，但不会匹配到byte和short类型的重载
 * 变长参数的重载优先级是最低的
 * @author maoyz
 */
public class Overload {

    // 6
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    // 2 char -> int
    public static void sayHello(int arg) {
        System.out.println("arg = " + arg + ", hello int");
    }

    // 3 char -> long
    public static void sayHello(long arg) {
        System.out.println("arg = " + arg + ",hello long");
    }

    // 4
    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    // 1
    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    // 6
    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

    // 5
    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}