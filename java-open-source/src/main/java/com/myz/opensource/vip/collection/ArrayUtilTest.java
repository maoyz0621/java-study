/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.vip.collection;


import com.vip.vjtools.vjkit.collection.ArrayUtil;
import com.vip.vjtools.vjkit.number.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * 数组工具类.
 * <p>
 * 1. 创建Array的函数
 * <p>
 * 2. 数组的乱序与contact相加
 * <p>
 * 3. 从Array转换到Guava的底层为原子类型的List
 * <p>
 * JDK Arrays的其他函数，如sort(), toString() 请直接调用
 * <p>
 * Common Lang ArrayUtils的其他函数，如subarray(),reverse(),indexOf(), 请直接调用
 *
 * @author maoyz0621 on 19-4-20
 * @version: v1.0
 */
public class ArrayUtilTest {

    @Test
    public void shuffle() {
        String[] arrays = {"d", "a", "c", "b", "e", "i", "g"};
        // 数组copy
        String[] arrayCopy = Arrays.copyOf(arrays, arrays.length);
        // 数组排序
        Arrays.sort(arrays);
        descArray(arrays);
        assertThat(arrays).containsExactly("a", "b", "c", "d", "e", "g", "i");

        System.out.println("************** 重排序 ArrayUtil.shuffle(arrays) ************");

        // 进行重排序
        ArrayUtil.shuffle(arrays);
        descArray(arrays);
        Assert.assertNotEquals(arrayCopy, arrays);

        System.out.println("************** 重排序 ArrayUtil.shuffle(arrays, random) ************");
        ArrayUtil.shuffle(arrays, RandomUtil.secureRandom());
        descArray(arrays);
    }

    @Test
    public void asList() {
        // 注意转换后的List不能写入, 否则抛出UnsupportedOperationException
        List<String> list = ArrayUtil.asList("d", "a", "c", "b", "e", "i", "g");
        System.out.println(list);

        List<Integer> integers = ArrayUtil.intAsList(11, 2, 5);
        System.out.println(integers);

        List<Long> longs = ArrayUtil.longAsList(20L, 1L, 12L);
        System.out.println(longs);

        List<Double> doubles = ArrayUtil.doubleAsList(30.0D, 23.33D, 54.33D);
        System.out.println(doubles);
    }

    @Test
    public void concat() {
        String[] arrays = {"d", "a", "c", "b", "e", "i", "g"};
        // 添加元素到数组头.
        String[] concatFirst = ArrayUtil.concat("1", arrays);
        descArray(concatFirst);

        System.out.println("************************");
        // 添加元素到数组末尾.
        String[] concatLast = ArrayUtil.concat(arrays, "0");
        descArray(concatLast);
    }

    private void descArray(String[] arrays) {
        for (String array : arrays) {
            System.out.print(array + " ");
        }
    }
}
