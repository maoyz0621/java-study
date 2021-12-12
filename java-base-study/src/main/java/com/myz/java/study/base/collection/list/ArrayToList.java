/**
 * Copyright 2021 Inc.
 **/
package com.myz.java.study.base.collection.list;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author maoyz0621 on 2021/12/9
 * @version v1.0
 */
public class ArrayToList {

    /**
     * 将array转为list,Arrays.asList(arr);
     */
    @Test
    public void array2List() {
        int[] arr = {1, 2, 3, 4};
        List<int[]> ints = Arrays.asList(arr);
        System.out.println(ints);
    }

    /**
     * 使用Arrays.asList将array转成list之后，ArrayList = java.util.Arrays.ArrayList，不是java.util.ArrayList，没有重写add()方法
     */
    @Test
    public void array2List1() {
        Integer[] arr = {1, 2, 3, 4};
        List<Integer> list = Arrays.asList(arr);
        // throw new UnsupportedOperationException();
        list.add(5);
        System.out.println(list);
    }

    /**
     * 将数组合并到list中 CollectionUtils.mergeArrayIntoCollection
     */
    @Test
    public void array2List2() {
        int[] arr = {1, 2, 3, 4};
        List<Integer> list = Lists.newArrayListWithExpectedSize(arr.length);
        CollectionUtils.mergeArrayIntoCollection(arr, list);
        System.out.println(list);
    }
}