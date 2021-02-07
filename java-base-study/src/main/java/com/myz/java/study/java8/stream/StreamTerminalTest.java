/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.java8.stream;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Terminal（结束操作）
 * 一个Stream只有一个Terminal操作，如前面开头的那个例子，其中forEach就是Terminal操作，Terminal操作是Stream的最后一个操作，这时候才会开始Stream的遍历。
 * forEach()  collect()  Match()  count()  reduce()
 *
 * @author maoyz0621 on 2019/3/18
 * @version v1.0
 */
public class StreamTerminalTest {

    /**
     * findFirst
     */
    @Test
    public void testFindFirst() {
        List<String> list = Arrays.asList("apple", "banana", "orange", "blueberry", "blackberry");
        Optional<String> first = list.stream()
                .filter(s -> s.startsWith("b"))
                .findFirst();
        System.out.println(first.isPresent());

        Optional<String> first0 = list.stream()
                .filter(s -> s.startsWith("c"))
                .findFirst();
        System.out.println(first0.isPresent());
    }

    @Test
    public void testCollect() {
        List<String> list = Arrays.asList("apple", "banana", "orange", "blueberry", "blackberry");
        List<String> b = list.stream()
                .filter(s -> s.startsWith("b"))
                .collect(Collectors.toList());
        System.out.println(b);

        // 转map
        Map<String, String> map = list.stream()
                .filter(s -> s.startsWith("b"))
                .collect(Collectors.toMap(k -> k, k -> k + "-val"));
        System.out.println(map);

        // 基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] ints = {1, 2, 3};
        List myList = Arrays.stream(ints).boxed().collect(Collectors.toList());
        System.out.println(myList);
    }

    /**
     * reduce()
     * Optional<T> reduce(BinaryOperator<T> accumulator)
     * T reduce(T identity, BinaryOperator<T> accumulator)
     * <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner)
     * <p>
     * identity: 一个初始化的值；这个初始化的值其类型是泛型U，与Reduce方法返回的类型一致；注意此时Stream中元素的类型是T，与U可以不一样也可以一样，这样的话操作空间就大了；
     * 不管Stream中存储的元素是什么类型，U都可以是任何类型，如U可以是一些基本数据类型的包装类型Integer、Long等；或者是String，又或者是一些集合类型ArrayList等
     * 如果缺少该参数，则没有默认值或初始值，并且它返回 optional
     * <p>
     * accumulator: （累加器）其类型是BiFunction，输入是U与T两个类型的数据，而返回的是U类型；
     * 也就是说返回的类型与输入的第一个参数类型是一样的，而输入的第二个参数类型与Stream中元素类型是一样的。
     * <p>
     * combiner: （组合器）其类型是BinaryOperator，支持的是对U类型的对象进行操作；
     * 擅长的是生成一个值
     * <p>
     * 注意：非并行流下第三个参数没用。流中元素只有1个的情况下，即使指定parallel也不会走并行。
     * 使用三个参数的reduce，务必注意线程安全。
     */
    @Test
    public void testReduce() {
        List<String> list = Arrays.asList("a11", "a2", "c1", "d1");

        // 1个参数 -> Optional<T> reduce(BinaryOperator<T> accumulator), 筛选长度最长的元素
        Optional<String> reduce = list.stream().reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        System.out.println(reduce.get());

        // 2个参数 -> T reduce(T identity, BinaryOperator<T> accumulator) s1表示的初始值 "@", 其返回类型取决于初始值类型
        String reduce1 = list.stream().reduce("@", (s1, s2) -> s1 + " = " + s2);
        System.out.println(reduce1);

        // 3个参数 -> <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner), 求和
        Integer reduce2 = list.stream().reduce(1,
                (sum, str) -> sum + str.length(),
                (s1, s2) -> {
                    System.out.println("thread name = " + Thread.currentThread().getName());
                    return s1 + s2;
                });
        System.out.println(reduce2);

        System.out.println("=======================================");

        // 字符串拼接
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("字符串拼接concat = " + concat);

        // 最小值
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("最小值minVal = " + minValue);

        // 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println("有起始值 sumValue = " + sumValue);

        // 无起始值
        int sumValue0 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("无起始值 sumValue0 = " + sumValue0);
        // parallel()
        Integer reduce3 = list.stream().parallel().
                reduce(1,
                        (sum, str) -> sum + str.length(),
                        (s1, s2) -> {
                            System.out.println("thread name = " + Thread.currentThread().getName() + ", s1 = " + s1 + " ,s2 = " + s2);
                            return s1 + s2;
                        });
        System.out.println(reduce3);
    }
}
