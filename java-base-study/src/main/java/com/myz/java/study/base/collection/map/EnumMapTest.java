package com.myz.java.study.base.collection.map;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

/**
 * EnumMap
 * key必须为Enum
 *
 * @author maoyz on 18-3-1.
 */
public class EnumMapTest {

    @Test
    public void test() {
        // 创建一个具有指定键类型的空枚举映射
        Map<Season, String> map = new EnumMap<Season, String>(Season.class);
        map.put(Season.SPRING, "春天");
        map.put(Season.SUMMER, "夏天");
        map.put(Season.AUTUMN, "秋天");
        map.put(Season.WINTER, "冬天");
        // 4
        System.out.println(map.size());
        System.out.println(map);

    }

    /**
     * 枚举类
     */
    enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }

}
