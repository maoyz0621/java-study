/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.generic;

/**
 * @author maoyz0621 on 20-3-13
 * @version v1.0
 */
public class GenericStatic<T> {

    // 编译错误
    // private static T t;

    // 编译错误
    // public static T a(T t) {
    //     return null;
    // }

    /**
     * 这是一个泛型方法，在泛型方法中使用的T是自己在方法中定义的 T，而不是泛型类中的T
     */
    public static <T> T a(T t) {
        return null;
    }

    public static <B> B b(B b) {
        return null;
    }
}
