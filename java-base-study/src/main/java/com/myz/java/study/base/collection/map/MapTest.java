package com.myz.java.study.base.collection.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

/**
 * Map<>  put()  get()
 * 底层实现：数组 + 链表
 * 1  HashMap
 * 线程不安全，效率高,父类AbstractMap,允许使用null值和null键(最多1个)
 * 2  HashTable
 * 　 线程安全，效率低,父类Dictionary，不允许使用null值和null键
 *
 * @author myz
 */
public class MapTest {

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        // put(K key, V value)
        map.put("1", "demo");
        map.put("2", "b");
        // 重新赋值
        map.put("2", "c");
        map.put(null, "d");
        //　V get(Object key);
        System.out.println(map.get("2"));
        //　key值不存在,返回null
        System.out.println(map.get("3"));
        // HashMap的key值可以设置为null
        System.out.println(map.get(null));

        System.out.println("==============");

        //取出全部的key
        Set<String> key = map.keySet();
        System.out.println(key);

        System.out.println("==============");

        // 迭代输出Map值
        //1 Map装为Set
        Set<Entry<String, String>> set = map.entrySet();
        //2 调用iterator
        Iterator<Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            //3 调用Map.Entry中getkey()和getValue()
            System.out.print(entry.getKey() + ":");
            System.out.println(entry.getValue());
        }
    }


    // 统计字符串中每个字符出现的次数
    @Test
    public void count() {
        countChar("asdasd, sdfd ,df ");
        countWord(" I am demo good student, I will work hard !");
    }

    /**
     * 统计每个字母出现的次数
     *
     * @param word
     */
    public void countChar(String word) {
        word = word.trim().replace(",", "").replaceAll("\\s", "");
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < word.length(); i++) {
            char key = word.charAt(i);
            // map中不包含key时
            if (!map.containsKey(key)) {
                map.put(word.charAt(i), 1);
            } else {
                int count = map.get(key) + 1;
                map.put(word.charAt(i), count);
            }
        }

        Set<Entry<Character, Integer>> set = map.entrySet();
        Iterator<Entry<Character, Integer>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry<Character, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 统计每个单词出现的次数
     *
     * @param word
     */
    public void countWord(String word) {
        // 分割字符
        String[] words = word.trim().split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String splitWord = words[i].trim();
            String key = splitWord.replaceAll("\\W", "").replaceAll("\\s", "");
            if (("".equals(key))) {
                break;
            }
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }

        Set<Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + "-->" + next.getValue());
        }
    }

    /**
     * 1 Map<String, String> map1 = null; 此时出现NullPointerException
     * 2 Map<String, String> map1 = new HashMap<>(); 跳过迭代循环
     */
    @Test
    public void testMapNull() {
        Map<String, String> map = null;
        map = Optional.ofNullable(map).orElse(new HashMap<>());
        Objects.requireNonNull(map);
        forMap(map);
    }

    private void forMap(Map<String, String> map) {
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key = " + key + ", value = " + value);
        }

        map.forEach((key, value) -> System.out.println("key = " + key + ", value = " + value));
    }

    @Test
    public void testMapNew() {
        Map<String, String> map = new HashMap<>();
        forMap(map);

    }

    /**
     * Collections.EMPTY_MAP, 此时的Map只做展示, 不能操作
     */
    @Test
    public void testEmptyMap() {
        Map<String, String> map = Collections.EMPTY_MAP;
        System.out.println(map);
        // UnsupportedOperationException
        map.putIfAbsent("key1","value1");
        map.forEach((key, value) -> System.out.println("key = " + key + ", value = " + value));
    }


}
