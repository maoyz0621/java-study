/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.java8.stream;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream
 * 对JAVA集合（Collection）对象功能的增强，方便对集合进行各类操作（过滤、求最大值、最小值、统计等）；
 * 更加高效，提供串行和并行两种模式，并行模式利用了Java中的fork/join框架技术，能充分利用多核处理器，提高程序并发性；
 * <p>
 * 不是一个数据结构
 * 为lambda表达式设计
 * 不支持索引访问
 * 很方便的作为数组或集合输出
 * 支持惰性访问
 * 并行计算
 *
 * @author maoyz0621 on 2019/3/18
 * @version: v1.0
 */
public class StreamTest {

    /**
     * Intermediate（中间操作）
     * Stream可以进行多次的Intermediate操作，如前面开头的那个例子，其中filter、map、sorted都是Intermediate操作，注意该操作是惰性化的，当调用到该方法的时候，
     * 并没有真正开始Stream的遍历。
     * <p>
     * Terminal（结束操作）
     * 一个Stream只有一个Terminal操作，如前面开头的那个例子，其中forEach就是Terminal操作，Terminal操作是Stream的最后一个操作，这时候才会开始Stream的遍历。
     * forEach()  collect()  Match()  count()  reduce()
     */
    @Test
    public void test() {
        List<String> list = Arrays.asList("a1", "a2", "c1", "d1");

        System.out.println("======== limit() ==========");
        list.stream().limit(2).forEach(System.out::println);

        System.out.println("======== filter() ==========");
        list.stream()
                .filter((s) -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        System.out.println("======== count() ==========");

        long a = list.stream()
                .filter((s) -> s.startsWith("a"))
                .map(String::toUpperCase)
                .count();
        System.out.println(a);

        System.out.println("======== anyMatch() ==========");

        boolean anyMatch = list.stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch);

        System.out.println("======== allMatch() ==========");

        boolean allMatch = list.stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allMatch);

        System.out.println("======== noneMatch() ==========");

        boolean noneMatch = list.stream()
                .noneMatch((s) -> s.startsWith("a"));
        System.out.println(noneMatch);

        System.out.println("======== reduce() ==========");

        Optional<String> reduce = list.stream()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);
    }

    /**
     * 构造Stream流的方式
     * Stream.of()
     */
    @Test
    public void test1() {
        // 创建Stream,底层 Arrays.stream(values);
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
        stream.forEach(System.out::println);
        // stream.forEach(s -> System.out.println(s));
    }

    /**
     * Arrays.stream()
     */
    @Test
    public void test2() {
        // 不能使用包装类,int
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.forEach(s -> System.out.println(s));
    }

    /**
     * IntStream.range
     * 随机数
     */
    @Test
    public void test3() {
        IntStream intStream = IntStream.range(1, 7);
        intStream.forEach(System.out::println);

        System.out.println("========================");

        intStream = new Random().ints(1, 5);
        intStream.forEach(System.out::println);
    }

    /**
     * 并发parallelStream
     */
    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> stream = list.parallelStream();
        Integer[] integers = stream.filter((s) -> s % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.asList(integers));
    }
}
