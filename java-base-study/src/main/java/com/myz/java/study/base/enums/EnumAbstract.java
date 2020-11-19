/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-19 17:36 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.base.enums;

/**
 * 枚举 包含abstract抽象方法,枚举值需要实现 抽象方法
 *
 * @author maoyz
 */
public enum EnumAbstract {

    /**
     * 加
     */
    PLUS {
        @Override
        public int calculate(int x, int y) {
            return x + y;
        }
    },

    // 乘法运算
    TIMES {
        @Override
        public int calculate(int x, int y) {
            return x * y;
        }
    };

    /**
     * 枚举类定义一个抽象方法，枚举类中所有的枚举值都必须实现这个方法
     *
     * @param x
     * @param y
     * @return
     */
    protected abstract int calculate(int x, int y);

    public static void main(String[] args) {
        int calculate0 = EnumAbstract.PLUS.calculate(1, 2);
        System.out.println(calculate0);
        int calculate1 = EnumAbstract.TIMES.calculate(3, 2);
        System.out.println(calculate1);
    }
}
