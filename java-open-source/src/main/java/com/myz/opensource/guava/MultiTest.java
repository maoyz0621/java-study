package com.myz.opensource.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * MultiSet　无序　可重复数据
 * 创建　HashMultiset.create()
 * 获取元素　elementSet();
 * 元素个数　count(Object)
 * <p>
 * MultiMap
 * key可重复，value数组形式
 * <p>
 * BiMap
 * 双向Map，key和value都不能重复
 * inverse()反转BiMap<K, V>的键值映射
 * 保证值是唯一的，因此values()返回Set而不是普通的Collection
 *
 * @author maoyz on 18-3-3.
 */
public class MultiTest {

    /**
     * MultiSet
     * 无序　可重复
     */
    @Test
    public void testMultiSet() {
        String[] words = "I am demo good student, and I like demo dog".split(" ");
        // 创建MultiSet()
        Multiset<String> set = HashMultiset.create();
        // Multiset分别添加元素,可以重复数据
        for (String word : words) {
            set.add(word);
        }

        //　获取元素
        Set<String> stringSet = set.elementSet();
        for (String s : stringSet) {
            // count()元素的个数
            System.out.println(s + "-->" + set.count(s));
        }
    }

    /**
     * MultiMap
     * 重复key,value以数组形式
     */
    @Test
    public void testMultiMap() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("demo", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "1");
        map.put("e", "2");

        // 创建Multimap,key可以重复
        Multimap<String, String> multimap = ArrayListMultimap.create();

        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> stringEntry = entryIterator.next();
            String key = stringEntry.getKey();
            String value = stringEntry.getValue();

            // 将原map中key和map对调
            multimap.put(value, key);
        }

        //　获取key
        Set<String> objects = multimap.keySet();
        for (String key : objects) {
            System.out.println(key + "" + multimap.get(key));
        }
    }

    /**
     * BiMap
     */
    @Test
    public void testBiMap() {
        BiMap<String, String> map = HashBiMap.create();
        // 此时<null, null>
        map.put("myz", "mao123");
        // 出现相同key
        map.put("myz", "mao456");
        map.put("aaa", "123456");

        // inverse()反转key和value
        BiMap<String, String> inverse = map.inverse();

        // null
        System.out.println(inverse.get("mao123"));
        // myz
        System.out.println(inverse.get("mao456"));
        // aaa
        System.out.println(inverse.get("123456"));

        Set<String> values = inverse.values();
        for (String value : values) {
            // myz aaa
            System.out.println(value);
        }
    }
}
