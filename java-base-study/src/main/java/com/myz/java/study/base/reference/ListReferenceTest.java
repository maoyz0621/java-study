/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.base.reference;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * List值传递
 *
 * @author maoyz0621 on 2022/11/7
 * @version v1.0
 */
public class ListReferenceTest {

    @Test
    public void testFilter() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        // [1, 2, 3, 4]
        System.out.println(list);
        System.out.println("hash=" + list.hashCode());

        System.out.println("\r\n======================\r\n");

        filter(list);
        System.out.println("\r\n======================\r\n");
        // [1, 2, 3, 4]
        System.out.println(list);
    }

    /**
     * 数据进行filter，值不变
     *
     * @param list
     */
    private void filter(List<Integer> list) {
        // 操作前：955331
        System.out.println("操作前hash=" + list.hashCode());

        list = list.stream().filter(param -> Objects.equals(2, param)).collect(Collectors.toList());

        System.out.println("操作后：" + list);
        // 操作后：33
        System.out.println("操作后hash=" + list.hashCode());
    }

    @Test
    public void testSort() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        // [1, 2, 3, 4]
        System.out.println(list);
        System.out.println("hash=" + list.hashCode());

        System.out.println("\r\n======================\r\n");

        sort(list);
        System.out.println("\r\n======================\r\n");
        // [1, 2, 3, 4]
        System.out.println(list);
    }

    /**
     * 数据进行filter，值不变
     *
     * @param list
     */
    private void sort(List<Integer> list) {
        // 操作前：955331
        System.out.println("操作前hash=" + list.hashCode());
        // 倒序
        list = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        // 操作后：[4, 3, 2, 1]
        // 操作后hash=1045631
        System.out.println("操作后：" + list);
        System.out.println("操作后hash=" + list.hashCode());
    }

    @Test
    public void testRemove() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        // [1, 2, 3, 4]
        System.out.println(list);
        System.out.println("hash=" + list.hashCode());

        System.out.println("\r\n======================\r\n");

        remove(list);
        System.out.println("\r\n======================\r\n");
        // [1, 3, 4]
        System.out.println(list);
    }

    /**
     * 数据del
     */
    private void remove(List<Integer> list) {
        // 操作前：955331
        System.out.println("操作前hash=" + list.hashCode());

        list.removeIf(param -> Objects.equals(param, 2));

        // 操作后：[1, 3, 4]
        // 操作后：30849
        System.out.println("操作后：" + list);
        System.out.println("操作后hash=" + list.hashCode());
    }

    @Test
    public void testAdd() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        // [1, 2, 3, 4]
        System.out.println(list);
        // hash=955331
        System.out.println("hash=" + list.hashCode());

        System.out.println("\r\n======================\r\n");

        add(list);
        System.out.println("\r\n======================\r\n");
        // [1, 2, 3, 4, 12]
        System.out.println(list);
    }

    /**
     * 数据add
     */
    private void add(List<Integer> list) {
        // 操作前：955331
        System.out.println("操作前hash=" + list.hashCode());

        list.add(12);

        // 操作后：[1, 2, 3, 4, 12]
        // 操作后hash=29615273
        System.out.println("操作后：" + list);
        System.out.println("操作后hash=" + list.hashCode());
    }
}