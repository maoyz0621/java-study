package com.myz.java.study.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyz
 */
public class Java8CollectionTest {

    @Test
    public void testRemoveIf() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list.removeIf(str -> str.length() > 2);
        System.out.println(list);
    }

    /**
     * list.sort()
     */
    @Test
    public void testSort() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list.sort((str1, str2) -> str1.length() - str2.length());
        System.out.println(list);
    }

    @Test
    public void testGetOrDefault() {
        Map<String, Object> param = new HashMap<>(4);
        param.put("a", 1);
        System.out.println(param.getOrDefault("a", 11));
        System.out.println(param.getOrDefault("b", 11));
    }

    /**
     * remove(k,v) 只有在当前`Map`中key正好映射到value时才删除该映射
     */
    @Test
    public void testRemove() {
        String a = "1-";
        String[] split = a.split("-");
        System.out.println(split[0]);
        System.out.println(split[1]);
    }

    /**
     * replace()
     */
    @Test
    public void testReplace() {
        Map<String, Object> param = new HashMap<>(4);
        param.put("a", 1);
        param.put("b", 2);
        // key存在才替换
        param.replace("a", 10);
        System.out.println(param.getOrDefault("a", 11));
        // key存在且等于旧值才替换
        param.replace("b", 3, "b");
        System.out.println(param.getOrDefault("b", 11));
    }

    /**
     * replaceAll()
     */
    @Test
    public void testReplaceAll() {
        Map<String, Object> param = new HashMap<>(4);
        param.put("a", 1);
        param.put("b", 2);
        param.replaceAll((k, v) -> v + ";");
        System.out.println(param);
    }

    /**
     * 1. 如果`Map`中`key`对应的映射不存在或者为`null`，则将`value`（不能是`null`）关联到`key`上；
     * 2. 否则执行`remappingFunction`，如果执行结果非`null`则用该结果跟`key`关联，否则在`Map`中删除`key`的映射
     */
    @Test
    public void testMerge() {
        Map<String, Object> param = new HashMap<>(4);
        param.put("a", 1);
        param.put("b", 2);

        // v1 -> 原本存在的非null， v2 -> 期望merge的值
        param.merge("a", "a", (v1, v2) -> v1 + ":" + v2);
        // remappingFunction执行结果 null, 则删除key
        param.merge("b", "a", (v1, v2) -> null);
        // 对应的映射不存在或者为`null`
        param.merge("c", "a", (v1, v2) -> v1 + ":" + v2);

        // {a=1:a, c=a}
        System.out.println(param);
    }

}
