/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.base.box;

import java.util.Date;

/**
 * 三目运算
 * @author maoyz0621 on 2022/7/21
 * @version v1.0
 */
public class ConditionalOperatorTest {

    public static void main(String[] args) {
        int i = 50;
        String a = String.valueOf(i > 60 ? 70 : 80);
        String b = String.valueOf(i > 60 ? 70 : 80.0);
        // false
        System.out.println(a.equals(b));

        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(new Date().getTime());
    }
}