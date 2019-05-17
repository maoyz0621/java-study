/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.collection.SetUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author maoyz0621 on 19-4-21
 * @version: v1.0
 */
public class ListUtilTest {

    @Test
    public void guavaBuildList() {
        ArrayList<String> list = ListUtil.newArrayList("a", "1");
        System.out.println(list);

        ArrayList<String> list1 = ListUtil.newArrayList(SetUtil.newHashSet("a", "2", "a"));
        System.out.println(list1);

        // 创建初始容量的List
        ArrayList<Object> list2 = ListUtil.newArrayListWithCapacity(8);
        System.out.println(list2.size());

        List list3 = ListUtil.newCopyOnWriteArrayList("a", "v");
    }

    @Test
    public void jdkBuild() {
        // 空的List
        List<Object> emptyList = ListUtil.emptyList();
        assertThat(emptyList).hasSize(0);

        List<Object> listIfNull = ListUtil.emptyListIfNull(null);
        assertThat(listIfNull).isNotNull().hasSize(0);

        List<Object> list3 = ListUtil.emptyListIfNull(emptyList);
        assertThat(list3).isSameAs(emptyList);

        // 返回只含一个元素但结构特殊的List，节约空间.
        List<String> list4 = ListUtil.singletonList("a");
        assertThat(list4).hasSize(1).contains("a");

        // 返回包装后不可修改的List.
        List<Object> list5 = ListUtil.unmodifiableList(list3);

        List<Object> synchronizedList = ListUtil.synchronizedList(list3);
        System.out.println(synchronizedList);
    }

    @Test
    public void general() {
        List<Object> list1 = Lists.newArrayList();
        List<String> list2 = ListUtil.newArrayList("a", "b", "c");
        List<String> list3 = ListUtil.newArrayList("a");

        System.out.println("**************** isEmpty() *****************");

        // true
        System.out.println(ListUtil.isEmpty(list1));
        // false
        System.out.println(ListUtil.isEmpty(list2));
        // true
        System.out.println(ListUtil.isEmpty(null));

        System.out.println("**************** isNotEmpty() *****************");

        // false
        System.out.println(ListUtil.isNotEmpty(list1));
        // true
        System.out.println(ListUtil.isNotEmpty(list2));
        // false
        System.out.println(ListUtil.isNotEmpty(null));

        System.out.println("************* getFirst() getLast() **********************");

        // null
        System.out.println(ListUtil.getFirst(list1));
        System.out.println(ListUtil.getFirst(list2));
        System.out.println(ListUtil.getLast(list2));
        System.out.println(ListUtil.getFirst(list3));
        System.out.println(ListUtil.getLast(list3));
    }

    @Test
    public void sortAndSearch() {
        List<String> list = ListUtil.newArrayList("d", "a", "c", "b", "e", "i", "g");

        System.out.println("************* 排序 sort() **************");

        // 排序
        ListUtil.sort(list);
        System.out.println(list);

        System.out.println("************* 洗牌 shuffle() **************");
        // 洗牌
        ListUtil.shuffle(list);
        System.out.println(list);

        // 按照一定规则排序
        ListUtil.sort(list, Ordering.natural());
        System.out.println(list);

        System.out.println("************* 二分法查找 binarySearch() **************");
        int e = ListUtil.binarySearch(list, "e");
        System.out.println(e);
        int e1 = ListUtil.binarySearch(list, "e", Ordering.natural());
        System.out.println(e1);
        //  如果不存在，返回一个负数，代表如果要插入这个对象，应该插入的位置
        int e2 = ListUtil.binarySearch(list, "e1");
        System.out.println(e2);

        System.out.println("************* 倒序 reverse() **************");
        List<String> reverse = ListUtil.reverse(list);
        System.out.println(reverse);

        ListUtil.sortReverse(list);
        System.out.println(list);
    }

    @Test
    public void collectionCalc() {
        List<String> list1 = ListUtil.newArrayList("1", "2", "3", "6", "6");
        List<String> list2 = ListUtil.newArrayList("4", "5", "6", "7", "6", "6");

        List<String> union = ListUtil.union(list1, list2);
        System.out.println("并集:" + union);

        List<String> intersection = ListUtil.intersection(list1, list2);
        System.out.println("交集:" + intersection);

        List<String> difference = ListUtil.difference(list1, list2);
        System.out.println("差集:" + difference);

        List<String> disjoint = ListUtil.disjoint(list1, list2);
        System.out.println("补集:" + disjoint);
    }
}
