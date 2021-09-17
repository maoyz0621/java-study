/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.collection.list;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * List拷贝
 *
 * @author maoyz0621 on 19-4-3
 * @version v1.0
 */
public class ListCopyTest {

    @Test
    public void copy01() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        System.out.println("源数据 = " + list);
        modify(list);
        System.out.println(list);
    }

    void modify(List<Integer> list) {
        List<Integer> listNew = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            // 对list操作
            if (next == 2){
                iterator.remove();
                listNew.add(next);
            }
        }

        listNew.addAll(list);
        // 重新赋值
        list = listNew;
    }

    /**
     * 使用ArrayList的构造器
     * 浅拷贝
     */
    @Test
    public void copy1() {
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4);
        System.out.println("源数据 = " + lists);
        List<Integer> dtsList = new ArrayList<>(lists);
        System.out.println("copy 后 " + dtsList);

        lists.add(5);
        System.out.println("源数据进行操作后 = " + lists);
        System.out.println(dtsList);
    }

    /**
     * 使用ArrayList的构造器
     * 浅拷贝
     */
    @Test
    public void copy2() {
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4);
        System.out.println("源数据 = " + lists);
        // 错误做法, 此时只是定义初始空间
        // if (srcSize > dest.size())
        //             throw new IndexOutOfBoundsException("Source does not fit in dest");
        // List<Integer> dtsList = new ArrayList<>(lists.size());
        List<Integer> dtsList = new ArrayList<>(Arrays.asList(new Integer[lists.size()]));
        Collections.copy(dtsList, lists);
        System.out.println("copy 后 " + dtsList);

        lists.add(5);
        System.out.println("源数据进行操作后 = " + lists);
        System.out.println(dtsList);
    }
}
