/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.opensource.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyz0621 on 2019/3/25
 * @version: v1.0
 */
public class UnionTest {

    /**
     * Maps.filterValues() 过滤值
     */
    @Test
    public void filterMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("张三", 19);
        map.put("李四", 20);
        map.put("王五", 21);

        Map<String, Integer> filterMap = Maps.filterValues(map, val -> val > 20);

        // Map:{张三=19, 李四=20, 王五=21}
        System.out.println("Map :" + map);
        // filterMap:{王五=21}
        System.out.println("filterMap :" + filterMap);
    }

    /**
     * 连接符 Joiner
     * skipNulls() 跳过Null
     * 连接 join()
     * 设置键值的连接符以及键与值之间的连接符withKeyValueSeparator()
     */
    @Test
    public void joiner() {
        // 设置连接符
        Joiner joiner = Joiner.on(",");
        // skipNulls() 跳过Null
        String join = joiner.skipNulls().join("你好", null, "haha");
        System.out.println(join);

        Map<String, String> map = new HashMap<String, String>();
        map.put("张三", "你好");
        map.put("李四", "嗨");

        // 设置键值的连接符以及键与值之间的连接符withKeyValueSeparator() 
        // join(Map)
        String join1 = joiner.withKeyValueSeparator(":").join(map);
        System.out.println(join1);
    }

    /**
     * Splitter 分隔符
     * 按字符分割 on()
     * 按固定长度分割 fixedLength()
     */
    @Test
    public void splitter() {
        String str = "a,b,c,d,e";
        // 按字符分割on()
        for (String word : Splitter.on(",").split(str)) {
            System.out.println(word);
        }

        System.out.println("==============");

        // 按固定长度分割 fixedLength()
        for (String word : Splitter.fixedLength(2).split(str)) {
            System.out.println(word);
        }
    }

    /**
     * 数值大小
     * Ints
     */
    @Test
    public void integer() {
        int[] arrs = {1, 2, 3, 4, 5};
        int max = Ints.max(arrs);
        int min = Ints.min(arrs);
        System.out.println(max);
        System.out.println(min);

    }
}
