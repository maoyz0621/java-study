package com.myz.java.study.java8.stream;

import com.myz.java.study.base.collection.domain.User;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream.generate()
 *
 * @author maoyz
 */
public class CollectorsTest {

    /**
     * Stream.generate 可以批量生成数据
     */
    @Test
    public void testGenerate() {
        // 生成一批测试数据
        Stream.generate(new UserSupplier()).limit(100)
                .forEach((user) -> System.out.println(user.getName() + " : " + user.getAge()));
    }

    /**
     * Collectors.groupingBy 分组, key 为判断条件
     */
    @Test
    public void testGroupingBy() {
        Map<Integer, List<User>> collect = Stream.generate(new UserSupplier()).limit(100)
                .collect(Collectors.groupingBy(User::getAge));

        collect.forEach((key, val) -> System.out.println(key + " " + val));
    }

    /**
     * Collectors.partitioningBy 分组， key为boolean
     */
    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<User>> collect = Stream.generate(new UserSupplier()).limit(100)
                .collect(Collectors.partitioningBy(user -> user.getAge() < 25 && user.getAge() > 18));

        collect.forEach((key, val) -> {
            System.out.println(key + " " + val.size());
        });
    }

    @Test
    public void tesToList() {
        List<User> collect = Stream.generate(new UserSupplier()).limit(10)
                .collect(Collectors.toList());
        System.out.println(collect);

        collect.forEach((key) -> System.out.println(key));
    }

    @Test
    public void testToSet() {
        Set<User> collect = Stream.generate(new UserSupplier()).limit(10).collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void testToConcurrentMap() {
        ConcurrentMap<String, User> collect = Stream.generate(new UserSupplier()).limit(10).collect(Collectors.toConcurrentMap(User::getName, user -> user));
        System.out.println(collect);

        System.out.println("\r\n===========================\r\n");

        Stream<String> s = Stream.of("apple", "banana", "orange");
        ConcurrentMap<Character, String> m = s.collect(
                Collectors.toConcurrentMap(s1 -> s1.charAt(0),
                        String::toUpperCase));
        // {a=APPLE, b=BANANA, o=ORANGE}
        System.out.println(m);

        System.out.println("\r\n===========================\r\n");

        s = Stream.of("apple", "banana", "apricot", "array", "orange");
        m = s.collect(
                Collectors.toConcurrentMap(s1 -> s1.charAt(0),
                        String::toUpperCase,
                        (s1, s2) -> s1 + " | " + s2));
        // {a=APPLE|APRICOT|ARRAY, b=BANANA, o=ORANGE}
        System.out.println(m);

        System.out.println("\r\n===========================\r\n");

        s = Stream.of("apple", "banana", "apricot", "array", "orange");
        m = s.collect(
                Collectors.toConcurrentMap(s1 -> s1.charAt(0),
                        String::toUpperCase,
                        (s1, s2) -> s1 + " | " + s2,
                        ConcurrentHashMap::new));
        System.out.println(m);
    }
}
