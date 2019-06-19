package com.myz.java.study.base.collection.collections;

import org.junit.Test;

import java.util.*;

/**
 * 1 emptyXxx() 返回空的列表（不可变的）
 * <p>
 * 2 返回一个只包含指定对象的不可变
 * singleton(T o)
 * singletonList(T o)
 * singletonMap(K key, V value)
 * <p>
 * 3 返回指定列表的不可修改视图
 * unmodifiableXxx(Xxx)
 *
 * @author maoyz on 18-3-1.
 */
public class UnsupportedOperationTest {

    /**
     * 空列表
     */
    @Test
    public void testEmpty() {
        // 定义一个空list
        List<Object> list = Collections.emptyList();
        // UnsupportedOperationException
        list.add(1);
    }

    /**
     * 返回一个只包含指定对象的不可变列表
     */
    @Test
    public void testSingleton() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Set<List<Integer>> singletonSet = Collections.singleton(list);

        System.out.println(singletonSet);
        // UnsupportedOperationException
        singletonSet.add(new ArrayList<>());
    }

    /**
     * 　返回指定列表的不可修改视图
     */
    @Test
    public void testUnmodifiable() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "demo");
        map.put("2", "b");
        map.put("3", "c");

        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(map);
        // UnsupportedOperationException
        unmodifiableMap.put("4", "d");
    }
}
