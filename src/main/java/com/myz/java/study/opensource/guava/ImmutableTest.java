package com.myz.java.study.opensource.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

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
     * ImmutableSet.copyOf(E[])
     */
    @Test
    public void testSet() {
        ImmutableSet<String> set = ImmutableSet.copyOf(new String[]{"demo", "b", "demo"});
        System.out.println(set);
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
