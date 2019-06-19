/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author maoyz0621 on 19-6-9
 * @version: v1.0
 */
public class MathTest {

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

    /**
     * 浮点类型,为了确保精确度,使用BigDecimal(),并且传入字符串类型
     * 其中运算不能使用运算符号 + - * /
     */
    @Test
    public void testBigDecimal1() {
        // BigDecimal()  传入String类型才能精确计算
        BigDecimal a1 = new BigDecimal("0.03");
        BigDecimal b1 = new BigDecimal("0.02");
        // 0.05
        System.out.println(b1.add(a1));
        // 0.01
        System.out.println(a1.subtract(b1));
        // 0.0006
        System.out.println(a1.multiply(b1));
        System.out.println(a1.divide(b1, RoundingMode.HALF_UP));
    }

    /**
     * todo 切记
     * 浮点类型,为了确保精确度,使用BigDecimal(),并且传入字符串类型
     * 其中运算不能使用运算符号 + - * /
     * 当传入浮点数时 ...
     */
    @Test
    public void testBigDecimal2() {
        BigDecimal a2 = new BigDecimal(0.03d);
        BigDecimal b2 = new BigDecimal(0.02d);
        // 0.0499999999999999993061106096092771622352302074432373046875
        System.out.println(a2.add(b2));
        // 0.0099999999999999984734433411404097569175064563751220703125
        System.out.println(a2.subtract(b2));
        System.out.println(a2.multiply(b2));
        System.out.println(a2.divide(b2, RoundingMode.HALF_UP));
    }
}
