package com.myz.opensource.apachcommons;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.FilterIterator;
import org.apache.commons.collections4.iterators.LoopingIterator;
import org.apache.commons.collections4.iterators.UniqueFilterIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器的扩展
 * 1、IterableMap  mapIterator()
 * MapIterator 以后不再使用map.keySet.iterator访问
 * HashedMap
 * <p>
 * 2、去重迭代器
 * UniqueFilterIterator
 * <p>
 * 3、自定义的过滤器
 * FilterIterator   自定义的过滤器+Predicate
 * <p>
 * 4、循环迭代器
 * LoopingIterator
 * <p>
 * 5、数组迭代器
 * ArrayListIterator
 *
 * @author maoyz on 18-3-5.
 */
public class IterableTest {

    /**
     * 　IterableMap  mapIterator()
     * MapIterator 以后不再使用map.keySet.iterator访问
     * HashedMap
     */
    @Test
    public void testMap() {
        IterableMap<String, String> map = new HashedMap<>();
        map.put("demo", "1");
        map.put("b", "2");
        map.put("c", "3");
        // MapIterable
        MapIterator<String, String> mapIterator = map.mapIterator();

        while (mapIterator.hasNext()) {
            // 移动游标
            String key = mapIterator.next();
            // getKey()
            System.out.println(key + "-->" + mapIterator.getValue());
        }
    }

    /**
     * UniqueFilterIterator(Iterator) 去重迭代器
     */
    @Test
    public void testUnique() {
        List<String> list = new ArrayList<>();
        list.add("demo");
        list.add("b");
        list.add("demo");
        // 去掉重复的过滤器
        Iterator<String> it = new UniqueFilterIterator<>(list.iterator());

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * 自定义迭代器
     * FilterIterator(Iterator, Predicate)
     */
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("abcba");
        list.add("dad");
        list.add("dsfa");

        // 自定义的条件
        Predicate<String> predicate = (String value) -> {
            //回文判断
            return new StringBuilder(value).reverse().toString().equals(value);
        };

        //去重重复的过滤器
        Iterator<String> it = new FilterIterator(list.iterator(), predicate);

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * LoopingIterator 循环迭代器
     */
    @Test
    public void testLloop() {

        List<String> list = new ArrayList<>();
        list.add("refer");
        list.add("dad");
        list.add("sdafds");

        // 循环迭代器
        Iterator<String> it = new LoopingIterator<>(list);

        for (int i = 0; i < 5; i++) {
            // refer -->dad -->sdafds -->refer -->dad -->
            System.out.print(it.next() + " -->");
        }
    }


    /**
     * 数组迭代器
     */
    @Test
    public void testArray() {
        int[] str = {1, 2, 3, 4, 5};

        Iterator<Integer> it = new ArrayListIterator<>(str);

        // 也可以指定起始索引和结束索引
        // Iterator<Integer> it = new ArrayListIterator<>(str,1,3);
        while (it.hasNext()) {
            System.out.print(it.next() + " -->");
        }
    }
}
