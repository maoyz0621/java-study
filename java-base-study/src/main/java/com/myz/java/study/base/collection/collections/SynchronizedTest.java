package com.myz.java.study.base.collection.collections;

import org.junit.Test;

import java.util.*;

/**
 * 线程安全的
 * synchronizedList(List<T> list)
 * synchronizedSet(Set<T> s)
 * synchronizedMap(Map<K,V> m)
 *
 * @author maoyz on 18-3-1.
 */
public class SynchronizedTest {

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        // 包装为线程安全的list
        List<Integer> synchronizedList = Collections.synchronizedList(list);
    }

    @Test
    public void testSet() {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            set.add(i);
        }

        // 包装为线程安全的set
        Set<Integer> synchronizedSet = Collections.synchronizedSet(set);
    }
}
