package com.myz.opensource.guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;

/**
 * 不可变集合：
 * 当对象被不可信的库调用时，不可变形式是安全的；
 * 不可变对象被多个线程调用时，不存在竞态条件问题
 * 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 * 不可变对象因为有固定不变，可以作为常量来安全使用
 * <p>
 * ImmutableList ImmutableSet ImmutableMap
 * copyOf() ImmutableSet.copyOf(set)
 * of() ImmutableSet.of(“demo”, “b”, “c”)或 ImmutableMap.of(“demo”, 1, “b”, 2);
 *
 * @author maoyz on 18-3-2.
 */
public class ImmutableTest {

    /**
     * ImmutableList.of
     */
    @Test
    public void testList() {
        ImmutableList<String> list = ImmutableList.of("demo", "b", "demo");
        System.out.println(list);
        list.add("demo");
    }

    /**
     * 通过已经存在的集合
     * ImmutableSet.copyOf(E[])
     */
    @Test
    public void testImmutableSetCopyOf() {
        ImmutableSet<String> set = ImmutableSet.copyOf(new String[]{"demo", "b", "demo"});
        System.out.println(set);
        // java.lang.UnsupportedOperationException – always
        set.add("demo");
    }

    @Test
    public void testImmutableSetCopyOf1() {
        List<String> list = Lists.newArrayList("demo", "b", "demo1");
        ImmutableSet<String> set = ImmutableSet.copyOf(list);
        // [demo, b, demo1]
        System.out.println(set);
        // 原list新增元素之后
        list.add("a");
        // 不变[demo, b, demo1]
        System.out.println("原list新增元素之后:" + set);
    }

    /**
     * ImmutableSet.of
     */
    @Test
    public void testImmutableSetOf() {
        ImmutableSet<String> set = ImmutableSet.of("demo", "b", "demo");
        System.out.println(set);
        // java.lang.UnsupportedOperationException – always
        set.add("demo");
    }

    @Test
    public void testImmutableSetBuilder() {
        ImmutableSet<Object> set = ImmutableSet.builder().add("a").addAll(Sets.newHashSet("1", "2")).add(10).build();
        System.out.println(set);
        // java.lang.UnsupportedOperationException – always
        set.add("demo");
    }

    /**
     * ImmutableMap
     */
    @Test
    public void testMap() {
        ImmutableMap<String, String> map = ImmutableMap.of("demo", "1", "b", "2");
        System.out.println(map);
        map.put("c", "3");
    }
}
