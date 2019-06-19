/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.opensource.apachcommons;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * @author maoyz0621 on 2019/3/25
 * @version: v1.0
 */
public class LangTest {

    /**
     * 数组合并
     */
    @Test
    public void testArray() {
        // 1 合并两个数组: org.apache.commons.lang. ArrayUtils
        String[] s1 = new String[]{"1", "2", "3"};
        String[] s2 = new String[]{"a", "b", "1"};
        String[] addAll = ArrayUtils.addAll(s1, s2);
        for (String word : addAll) {
            System.out.println(word);
        }

        String toString = ArrayUtils.toString(addAll);
        System.out.println("toString = " + toString);
    }

    /**
     * ClassUtils
     */
    @Test
    public void testClassUtils() {
        System.out.println("取得类名 :" + ClassUtils.getShortClassName(LangTest.class));
        System.out.println("获取包名 :" + ClassUtils.getPackageName(LangTest.class));
    }

    /**
     * NumberUtils
     */
    @Test
    public void testNumber() {
        boolean creatable1 = NumberUtils.isCreatable("123");
        // true
        System.out.println(creatable1);

        boolean creatable2 = NumberUtils.isCreatable("123a");
        // false
        System.out.println(creatable2);

        boolean creatable3 = NumberUtils.isCreatable("123 2");
        // false
        System.out.println(creatable3);

        boolean creatable4 = NumberUtils.isCreatable("123.22");
        // true
        System.out.println(creatable4);
    }
}
