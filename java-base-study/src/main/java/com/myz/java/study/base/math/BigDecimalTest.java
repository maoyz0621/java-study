/**
 * Copyright 2021 Inc.
 **/
package com.myz.java.study.base.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author maoyz0621 on 2021/9/3
 * @version v1.0
 */
public class BigDecimalTest {

    @Test
    public void testBigDecimalStr() {
        // BigDecimal()  传入String类型才能精确计算
        BigDecimal a1 = new BigDecimal("0.0000000000003000");
        // 3.000E-13
        System.out.println("科学计数法：" + a1.toString());
        // 0.0000000000003000
        System.out.println(a1.toPlainString());
        // 0.0000000000003
        System.out.println("去除末尾多余的:" + a1.stripTrailingZeros().toPlainString());
        DecimalFormat format = new DecimalFormat("#0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        // 2.35
        String format1 = format.format(new BigDecimal("2.3456"));
        System.out.println("格式化：" + format1);
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
        System.out.println(a.setScale(2, RoundingMode.HALF_UP));
        BigDecimal b = new BigDecimal("-1.222222222222");
        System.out.println(b.setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * 银行家算法
     * 舍去位数值 < 5 直接舍去
     * 舍去位数值 > 5 直接进位
     * 舍去位数值 = 5
     * 1、5后面还有其他非0数值，进位
     * 2、5后面 = 0，看前一位是偶数舍，奇数进位
     */
    @Test
    public void testBank() {
        BigDecimal a = new BigDecimal("5.54");
        System.out.println(a.setScale(1, RoundingMode.HALF_EVEN));  // 5.5

        BigDecimal a2 = new BigDecimal("1.66");
        System.out.println(a2.setScale(1, RoundingMode.HALF_EVEN));   // 1.7

        BigDecimal a4 = new BigDecimal("1.06");

        System.out.println(a4.setScale(1, RoundingMode.HALF_EVEN));  // 1.1


        BigDecimal a1 = new BigDecimal("2.450");
        System.out.println(a1.setScale(1, RoundingMode.HALF_EVEN));   // 2.4

        BigDecimal a10 = new BigDecimal("2.550");
        System.out.println(a10.setScale(1, RoundingMode.HALF_EVEN));  // 2.6

        BigDecimal a11 = new BigDecimal("2.551");
        System.out.println(a11.setScale(1, RoundingMode.HALF_EVEN));  // 2.6
        BigDecimal a12 = new BigDecimal("2.555");
        System.out.println(a12.setScale(1, RoundingMode.HALF_EVEN));  // 2.6


        BigDecimal a3 = new BigDecimal("1.25");
        System.out.println(a3.setScale(1, RoundingMode.HALF_EVEN));  // 1.2
        BigDecimal a5 = new BigDecimal("1.55");
        System.out.println(a5.setScale(1, RoundingMode.HALF_EVEN));  // 1.6
    }

    @Test
    public void testEqu() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("1.00");
        System.out.println(a.doubleValue() == b.doubleValue()); // true
        System.out.println("BigDecimal compareTo = " + (a.compareTo(b) == 0));    // true
        System.out.println("BigDecimal equals = " + a.equals(b));    // false
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