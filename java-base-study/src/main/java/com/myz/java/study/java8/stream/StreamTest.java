/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.java8.stream;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        System.out.println("======== filter() 筛选复合条件的 ==========");
        list.stream()
                .filter((s) -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        System.out.println("======== count() , 计算数量 ==========");

        long a = list.stream()
                .filter((s) -> s.startsWith("a"))
                .map(String::toUpperCase)
                .count();
        System.out.println(a);

        System.out.println("======== anyMatch() 满足其中一个即可 ==========");

        boolean anyMatch = list.stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch);

        System.out.println("======== allMatch() 全部满足 ==========");

        boolean allMatch = list.stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allMatch);

        System.out.println("======== noneMatch() 都不满足 ==========");

        boolean noneMatch = list.stream()
                .noneMatch((s) -> s.startsWith("a"));
        System.out.println(noneMatch);
        boolean noneMatchb = list.stream()
                .noneMatch((s) -> s.startsWith("b"));
        System.out.println(noneMatchb);

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
        stream.forEach(System.out::println);
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

    /**
     * filter() 按照一个元素顺序执行判断, 结束之后, 下一个元素执行
     */
    @Test
    public void testFilter() {
        ArrayList<String> list = Lists.newArrayList("a1", "a2", "b1", "b2", "ab1");
        List<String> collect = list.stream()
                .filter((l) -> {
                    System.out.println("筛选1: " + l);
                    return l.contains("1");
                }).filter((l) -> {
                    System.out.println("筛选a: " + l);
                    return l.contains("a");
                }).map((l) -> {
                    System.out.println("map: " + l);
                    return l.toUpperCase();
                })
                .sorted().collect(Collectors.toList());
        System.out.println(collect);

        /*
        筛选1: a1
        筛选a: a1
        map: a1
        筛选1: a2
        筛选1: b1
        筛选a: b1
        筛选1: b2
        筛选1: ab1
        筛选a: ab1
        map: ab1
        [A1, AB1]*/
    }

    @Test
    public void test10() {
        char[] chars = "abc".toCharArray();
        System.out.println(chars);
        Stream.of(chars).forEach(System.out::println);
        // Arrays.stream(chars).forEach(System.out::println);
    }

}
