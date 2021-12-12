/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.collection.list;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.List;

/**
 * List和Array的互转
 *
 * @author maoyz0621 on 19-4-2
 * @version: v1.0
 */
public class ListToArrayTest {

    /**
     * 将list转为array，需要注意的是toArray() 不能得到基本数据类型，只能得到包装类型
     */
    @Test
    public void list2Array() {
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4);
        // 注意：强制转换时会抛异常
        // Integer[] array = (Integer[]) lists.toArray();

        // toArray()指定类型
        Integer[] array = lists.toArray(new Integer[lists.size()]);
        System.out.println(array);
    }

    /**
     * 将list转为array，若想得到基本数据类型，使用org.apache.commons.lang3.ArrayUtils
     */
    @Test
    public void list2ArrayPrimitive() {
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4);
        int[] ints = ArrayUtils.toPrimitive(lists.toArray(new Integer[0]));
        System.out.println(ints);
    }
}
