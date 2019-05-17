/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.base;

import com.vip.vjtools.vjkit.base.BooleanUtil;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-28
 * @version: v1.0
 */
public class BooleanUtilTest {

    /**
     * 只分析是否忽略大小写的"true", str为空时返回false
     */
    @Test
    public void toBoolean() {
        System.out.println(BooleanUtil.toBoolean("true"));
        System.out.println(BooleanUtil.toBoolean("tru"));
        System.out.println(BooleanUtil.toBoolean(""));
        System.out.println(BooleanUtil.toBoolean("null"));
        System.out.println(BooleanUtil.toBoolean(null));
    }

    /**
     * 使用标准JDK，只分析是否忽略大小写的"true", str为空时返回null
     */
    @Test
    public void toBooleanObject() {
        System.out.println(BooleanUtil.toBooleanObject("true"));
        System.out.println(BooleanUtil.toBooleanObject("tru"));
        System.out.println(BooleanUtil.toBooleanObject(""));
        System.out.println(BooleanUtil.toBooleanObject("null"));
        System.out.println(BooleanUtil.toBooleanObject(null));
    }

    /**
     * 支持true/false, on/off, y/n, yes/no的转换, str为空或无法分析时返回null
     */
    @Test
    public void parseGeneralString() {
        System.out.println(BooleanUtil.parseGeneralString("1"));
        System.out.println(BooleanUtil.parseGeneralString("y"));
        System.out.println(BooleanUtil.parseGeneralString("1", true));
        System.out.println(BooleanUtil.parseGeneralString("y", true));
    }

    /**
     * 取反
     */
    @Test
    public void negate() {
        System.out.println(BooleanUtil.negate(Boolean.TRUE));
        System.out.println(BooleanUtil.negate(Boolean.FALSE));
    }

    /**
     * 多个值的and,必须全部为true
     */
    @Test
    public void and() {
        System.out.println(BooleanUtil.and(Boolean.TRUE, false, false));
        System.out.println(BooleanUtil.and(Boolean.FALSE, true));
        System.out.println(BooleanUtil.and(Boolean.TRUE, true));
    }

    /**
     * 多个值的or,有一个true即可
     */
    @Test
    public void or() {
        System.out.println(BooleanUtil.or(Boolean.TRUE, false, false));
        System.out.println(BooleanUtil.or(Boolean.FALSE, true));
        System.out.println(BooleanUtil.or(Boolean.TRUE, true));
    }
}
