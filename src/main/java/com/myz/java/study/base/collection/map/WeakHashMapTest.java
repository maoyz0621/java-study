package com.myz.java.study.base.collection.map;

import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * WeakHashMap
 * 键为弱引用，立即被回收
 *
 * @author maoyz on 18-3-1.
 */
public class WeakHashMapTest {

    @Test
    public void test() {
        Map<String, String> map = new WeakHashMap<String, String>();
        map.put("1", "demo");
        map.put("2", "b");
        // 立即被回收
        map.put(new String("3"), "c");

        System.out.println("gc()前：" + map.size());

        System.out.println("gc()前：" + map.get("1"));
        System.out.println("gc()前：" + map.get("2"));
        System.out.println("gc()前：" + map.get("3"));

        System.out.println("-------------");

        System.gc();
        System.runFinalization();

        System.out.println("gc()后：" + map.size());
        System.out.println("gc()后：" + map.get("1"));
        System.out.println("gc()后：" + map.get("2"));
        System.out.println("gc()后：" + map.get("3"));
    }
}
