/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.opensource.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 布隆过滤器的原理是，当一个元素被加入集合时，通过K个Hash函数将这个元素映射成一个位数组中的K个点，把它们置为1。
 * 检索时，我们只要看看这些点是不是都是1就（大约）知道集合中有没有它了：
 * 如果这些点有任何一个0，则被检元素一定不在；
 * 如果都是1，则被检元素很可能在。
 *
 * @author maoyz0621 on 2019/3/25
 * @version: v1.0
 */
public class BloomFilterTest {

    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    @Test
    public void test() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        long startTime = System.nanoTime();
        if (bloomFilter.mightContain(299999)) {
            System.out.println("命中");
        }
        long endTime = System.nanoTime();

        System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "毫秒");
    }

    /**
     * 有误判率
     */
    @Test
    public void testError() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        List<Integer> list = new ArrayList<>(1000);
        // 故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }

}
