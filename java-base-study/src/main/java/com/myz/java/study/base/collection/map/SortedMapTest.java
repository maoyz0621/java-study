package com.myz.java.study.base.collection.map;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.SortedMap;

/**
 * 排序Map
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/29 14:11
 */
public class SortedMapTest {

    @Test
    public void test() {
        SortedMap<String, String> sortedMap = Maps.newTreeMap();
        sortedMap.put("a", "1");
        sortedMap.put("d", "2");
        sortedMap.put("e", "3");
        sortedMap.put("b", "4");
        // {a=1, b=4, d=2, e=3}
        System.out.println(sortedMap);


        // 截取：{a=1, b=4}
        System.out.println(sortedMap.subMap("a", "c"));

        // 第一个key: a
        System.out.println(sortedMap.firstKey());

        // 走后一个key: e
        System.out.println(sortedMap.lastKey());

        // 从头开始到key:{a=1, b=4}
        System.out.println(sortedMap.headMap("c"));

        // 从key开始到最后:{b=4, d=2, e=3}
        System.out.println(sortedMap.tailMap("b"));
    }
}
