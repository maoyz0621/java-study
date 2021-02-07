/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.optional;

/**
 * 判断是否基本数据类型
 * Class 提供的isPrimitive()
 *
 * @author maoyz0621 on 19-7-8
 * @version: v1.0
 */
public class PrimitiveDemo {

    public static void main(String[] args) {
        // 基本数据类型
        int i = 1;
        // 包装类型
        Integer j = 1;

        // true
        System.out.println(int.class.isPrimitive());
        // false
        System.out.println(j.getClass().isPrimitive());
        // true
        System.out.println(Integer.TYPE != Integer.class);
        // false
        System.out.println(int.class == Integer.class);
    }

    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     */
    public static boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

}
