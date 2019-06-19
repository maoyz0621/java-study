package com.myz.java.study.opensource.apachcommons;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.bidimap.DualTreeBidiMap;
import org.junit.Test;

/**
 * 双向Map 要求键与值都不能重复
 * BidiMap接口
 * inverseBidiMap()反转方法
 * 1、DualTreeBidiMap:有序
 * 2、DualHashBidiMp:无序
 *
 * @author maoyz on 18-3-5.
 */
public class MapTest {

    /**
     * DualHashBidiMap　无序双向map
     */
    @Test
    public void testHash() {
        // 无序
        BidiMap<String, String> map = new DualHashBidiMap<>();

        map.put("bbb", "mao123");
        // map.put("ccc","mao123");
        map.put("myz", "yue456");
        map.put("aaa", "123456");

        //　反转inverseBidiMap()
        MapIterator<String, String> mapIterator = map.inverseBidiMap().mapIterator();
        while (mapIterator.hasNext()) {
            mapIterator.next();
            System.out.println(mapIterator.getKey() + "-->" + mapIterator.getValue());
        }
    }


    /**
     * DualTreeBidiMap　有序双向map
     */
    @Test
    public void testTree() {
        // 有序
        BidiMap<String, String> map = new DualTreeBidiMap<>();

        map.put("bbb", "mao123");
        map.put("myz", "yue456");
        map.put("aaa", "123456");

        MapIterator<String, String> mapIterator = map.inverseBidiMap().mapIterator();

        while (mapIterator.hasNext()) {
            mapIterator.next();
            System.out.println(mapIterator.getKey() + "-->" + mapIterator.getValue());
        }
    }
}
