package com.myz.java.study.base.collection.map;

import org.junit.Test;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * IdentityHashMap
 * 只有在key完全相等(同一个引用),比较的地址引用==
 *
 * @author maoyz on 18-3-1.
 */
public class IdentityHashMapTest {

    @Test
    public void test() {
        Map<String, String> map = new IdentityHashMap<String, String>();
        map.put("demo", "1");
        map.put("demo", "2");
        // 1
        System.out.println(map.size());

        map.put(new String("demo"), "3");
        map.put(new String("demo"), "4");

        // 3
        System.out.println(map.size());
    }
}
