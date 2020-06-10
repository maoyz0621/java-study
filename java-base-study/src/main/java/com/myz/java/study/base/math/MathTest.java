/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

    /**
     * 浮点类型,为了确保精确度,使用BigDecimal(),并且传入字符串类型
     * 其中运算不能使用运算符号 + - * /
     * RoundingMode.HALF_UP   四舍五入
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
     * 使用BigDecimal.valueOf()
     */
    @Test
    public void testBigDecimal11() {
        double a = 0.05D;
        double b = 0.03D;
        // 0.08
        System.out.println(BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)));
        // 0.02
        System.out.println(BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b)));
        // 0.0015
        System.out.println(BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)));
        // java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        // System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b)));

        // 设置roundingMode
        System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 4));
        // 1.6667
        System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 4, RoundingMode.HALF_UP));
        // 1.6667
        System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 4, RoundingMode.UP));
        // 1.6666
        System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 4, RoundingMode.DOWN));
        // HALF_UP 四舍五入 2.3333
        System.out.println(BigDecimal.valueOf(0.07D).divide(BigDecimal.valueOf(0.03D), 4, RoundingMode.HALF_UP));
        // UP 2.3334
        System.out.println(BigDecimal.valueOf(0.07D).divide(BigDecimal.valueOf(0.03D), 4, RoundingMode.UP));
        // DOWN 2.3333
        System.out.println(BigDecimal.valueOf(0.07D).divide(BigDecimal.valueOf(0.03D), 4, RoundingMode.DOWN));
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
        System.out.println("a2 = " + a2);   // 0.0299999999999999988897769753748434595763683319091796875
        BigDecimal b2 = new BigDecimal(0.02d + "");
        System.out.println("b2 = " + b2);   // 0.02
        // 0.0499999999999999993061106096092771622352302074432373046875
        System.out.println(a2.add(b2));
        // 0.0099999999999999984734433411404097569175064563751220703125
        System.out.println(a2.subtract(b2));
        System.out.println(a2.multiply(b2));
        System.out.println(a2.divide(b2, RoundingMode.HALF_UP));
    }

    /**
     * 浮点比较大小 -1 表示小于，0 表示 等于， 1表示 大于
     */
    @Test
    public void testBigDecimalCompare() {
        BigDecimal a = new BigDecimal(0);
        BigDecimal b = new BigDecimal(0);
        // 0
        System.out.println(a.compareTo(b));
        BigDecimal c = new BigDecimal(1);
        System.out.println(c.compareTo(b));
    }

    /**
     * 保留小数
     */
    @Test
    public void test0() {
        BigDecimal a = new BigDecimal("1.222222222222");
        System.out.println(a.setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal b = new BigDecimal("-1.222222222222");
        System.out.println(b.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void testEqu() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("1.00");
        System.out.println(a.equals(b));    // false
        System.out.println(a.equals(b.setScale(0, BigDecimal.ROUND_HALF_UP)));    // true
        System.out.println(a.setScale(2, BigDecimal.ROUND_HALF_UP).equals(b));    // true
    }

    @Test
    public void testCompare() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("1.00");
        BigDecimal c = new BigDecimal("1.01");
        System.out.println(a.compareTo(b));    // 0
        System.out.println(a.equals(b));    // false
        System.out.println(b.compareTo(c));    // -1
        System.out.println(c.compareTo(a));    // 1
        System.out.println(new BigDecimal("0").compareTo(new BigDecimal("0.00")));    // 0
        System.out.println(new BigDecimal("0").equals(new BigDecimal("0.00")));    // false
        System.out.println(Objects.equals(a, b));    // false
    }
}