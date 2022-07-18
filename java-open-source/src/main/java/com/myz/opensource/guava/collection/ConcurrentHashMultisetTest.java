/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.guava.collection;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

/**
 * @author maoyz0621 on 2022/6/13
 * @version v1.0
 */
public class ConcurrentHashMultisetTest {

    @Test
    public void test() {
        Multiset<Integer> concurrentHashMultiset = ConcurrentHashMultiset.create();
        concurrentHashMultiset.add(1);
        concurrentHashMultiset.add(1);
        concurrentHashMultiset.add(2);
        concurrentHashMultiset.add(3);
        concurrentHashMultiset.add(3);


        System.out.println(concurrentHashMultiset.size());
        System.out.println(concurrentHashMultiset.count(1));
        System.out.println(concurrentHashMultiset.count(2));
        System.out.println(concurrentHashMultiset.count(3));

        System.out.println("=======================================");

        concurrentHashMultiset.remove(1);
        System.out.println(concurrentHashMultiset.size());
        System.out.println(concurrentHashMultiset.count(1));
        System.out.println(concurrentHashMultiset.count(2));
        System.out.println(concurrentHashMultiset.count(3));
    }
}