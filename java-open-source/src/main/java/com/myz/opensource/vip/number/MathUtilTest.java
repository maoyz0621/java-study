/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.vip.number;

import com.vip.vjtools.vjkit.number.MathUtil;
import org.junit.Test;

import java.math.RoundingMode;

/**
 * @author maoyz0621 on 19-4-24
 * @version: v1.0
 */
public class MathUtilTest {

    @Test
    public void test() {
        long val = 17L;
        System.out.println("往上找出最接近的2的倍数 " + MathUtil.nextPowerOfTwo(val));
        System.out.println("往下找出最接近的2的倍数 " + MathUtil.previousPowerOfTwo(val));
        System.out.println("是否2的倍数 " + MathUtil.isPowerOfTwo(val));
        System.out.println("当模为2的倍数时，用比取模块更快的方式计算 " + MathUtil.modByPowerOfTwo(17, 15));
    }

    @Test
    public void caculate() {
        System.out.println("取模 " + MathUtil.mod(17, 15));
        System.out.println("取模 " + MathUtil.mod(15L, 17L));
        System.out.println("次方 " + MathUtil.pow(17, 15));
        System.out.println("次方 " + MathUtil.pow(15L, 2));
        System.out.println("开方 " + MathUtil.sqrt(17, RoundingMode.HALF_UP));
        System.out.println("开方 " + MathUtil.sqrt(16, RoundingMode.HALF_UP));
        System.out.println("开方 " + MathUtil.sqrt(15L, RoundingMode.HALF_UP));
    }

    @Test
    public void divide() {
        System.out.println("除法 " + MathUtil.divide(17, 15, RoundingMode.HALF_UP));
        System.out.println("除法 " + MathUtil.divide(17L, 15L, RoundingMode.HALF_UP));
    }
}
