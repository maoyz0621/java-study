/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.math;

import org.junit.Test;

/**
 * BigDecimal的使用
 *
 * @author maoyz0621 on 19-6-9
 * @version v1.0
 */
public class MathTest {

    /**
     * 浮点计算，精度丢失
     */
    @Test
    public void test() {
        float a = 1.0f - .9f;
        float b = .9f - .8f;
        System.out.println(a); // 0.100000024
        System.out.println(b);  // 0.099999964
        System.out.println(a == b);  // false
    }

    /**
     * float类型的小数运算
     */
    @Test
    public void testFloat() {
        float a = 0.03f;
        float b = 0.02f;
        // 0.049999997
        System.out.println(a + b);
        // 0.01
        System.out.println(a - b);
        // 5.9999997E-4
        System.out.println(a * b);
        // 1.5
        System.out.println(a / b);
    }

    /**
     * double类型的运算
     */
    @Test
    public void testDouble() {
        double a = 0.03D;
        double b = 0.02D;
        //0.05
        System.out.println(a + b);
        // 0.009999999999999998
        System.out.println(a - b);
        //6.0E-4
        System.out.println(a * b);
        System.out.println(a / b);
    }
}