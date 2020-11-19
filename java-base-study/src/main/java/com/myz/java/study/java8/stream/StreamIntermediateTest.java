/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.java8.stream;


import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream
 * 对JAVA集合（Collection）对象功能的增强，方便对集合进行各类操作（过滤、求最大值、最小值、统计等）；
 * 更加高效，提供串行和并行两种模式，并行模式利用了Java中的fork/join框架技术，能充分利用多核处理器，提高程序并发性；
 * <p>
 * 不是一个数据结构, 为lambda表达式设计, 不支持索引访问, 很方便的作为数组或集合输出, 支持惰性访问, 并行计算
 *
 * @author maoyz0621 on 2019/3/18
 * @version v1.0
 */
public class StreamIntermediateTest {

    /**
     * Intermediate（中间操作）
     * Stream可以进行多次的Intermediate操作，如前面开头的那个例子，其中filter、map、sorted都是Intermediate操作，注意该操作是惰性化的，当调用到该方法的时候，
     * 并没有真正开始Stream的遍历。
     */
    @Test
    public void testMap() {
        List<String> list = Arrays.asList("a1", "a2", "c1", "d1");
        // map -> 新的Stream
        list.stream().map(String::toUpperCase).sorted().forEach(System.out::println);
        // 原数据不变
        System.out.println(list);
    }

    /**
     * flat 扁平的
     */
    @Test
    public void testFlatMap() {
        List<Integer> collect0 = Stream.of(1, 22, 33).flatMap(v -> Stream.of(v * v)).collect(Collectors.toList());
        System.out.println(collect0);

        List<Integer> collect1 = Stream.of(1, 22, 33).map(v -> v * v).collect(Collectors.toList());
        System.out.println(collect1);

        //flatMap的扁平化处理

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("1", "one");
        map1.put("2", "two");

        Map<String, String> map2 = new HashMap<>();
        map2.put("3", "three");
        map2.put("4", "four");
        list.add(map1);
        list.add(map2);

        // 收集map中的val
        Set<String> output = list.stream()
                // 收集val
                .map(Map::values)
                // val 转 String
                .flatMap(Collection::stream)
                // 转 Set
                .collect(Collectors.toSet());
        // [four, one, two, three]
        System.out.println(output);

        // 收集map中的key
        Set<String> collect = list.stream()
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        // [1, 2, 3, 4]
        System.out.println(collect);
    }

    /**
     * peek也是对流中的每一个元素进行操作，除了生成一个包含原所有元素的新Stream，还提供一个Consumer消费函数。
     * peek()可以做一些输出、外部处理、副作用等无返回值。生成一个包含原Stream的所有元素的新Stream，新Stream每个元素在被消费之前都会执行peek给定的消费函数;
     */
    @Test
    public void testPeek() {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = Stream.of(1, 2, 3, 4)
                .peek(list::add)
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(result);
        System.out.println(list);
    }

    @Test
    public void testFilter() {
        List<String> list = Arrays.asList("a1", "a2", "c1", "d1");
        String[] as = list.stream()
                .filter((s) -> s.startsWith("a"))
                .map(String::toUpperCase)
                .toArray(String[]::new);
        System.out.println(as.length);
    }

    /**
     * 去重
     */
    @Test
    public void testDistinct() {
        List<Integer> collect = Stream.of(1, 2, 3, 3, 3, 2, 4, 5, 6)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
