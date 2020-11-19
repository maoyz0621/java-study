package com.myz.java.study.base.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 数组Array
 * 1. 可以使用工具类Arrays
 * 2. 可以使用Stream流处理
 *
 * @author maoyz
 */
public class ArrayTest {

    int[] array = {1, 2, 2, 3};
    String[] arr1 = {"aa", "bbcc", "1"};
    String[][] deepArray = new String[][]{{"沉默", "王二"}, {"一枚有趣的程序员"}};

    @Test
    public void test() {
        System.out.println(array);
        System.out.println(arr1);
    }

    /**
     * 使用Arrays.asList() 将数组转成list
     */
    @Test
    public void testArrays() {
        System.out.println(Arrays.asList(array));
        System.out.println(Arrays.asList(arr1));
    }

    /**
     * Stream处理
     */
    @Test
    public void testStream() {
        Arrays.asList(arr1).stream().forEach(System.out::println);
        Arrays.stream(arr1).forEach(System.out::println);
        // 直接使用Stream
        Stream.of(arr1).forEach(System.out::println);
    }

    /**
     * 使用Arrays.toString()
     * TODO 可以学习其一波源码
     */
    @Test
    public void testToString() {
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 多维数组 Arrays.deepToString()
     */
    @Test
    public void testDeepToString() {
        // [[Ljava.lang.String;@7d6f77cc, [Ljava.lang.String;@5aaa6d82]
        System.out.println(Arrays.toString(deepArray));
        // [[沉默, 王二], [一枚有趣的程序员]]
        System.out.println(Arrays.deepToString(deepArray));
    }

    @Test
    public void testListToArray() {
        String[] s = new String[]{"dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"};
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        System.out.println(list);
        Object[] objects = list.toArray();
        System.out.println(objects);
        String[] strings = list.toArray(new String[0]);
        System.out.println(strings);
    }
}
