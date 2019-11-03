/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author maoyz0621 on 19-10-31
 * @version: v1.0
 */
public class Cas {

    @Test
    public void test(){
        AtomicInteger atomicInteger = new AtomicInteger(10);

        // 期待是10, true就update, false不update
        boolean compareAndSet = atomicInteger.compareAndSet(10, 1);

        System.out.println(compareAndSet+" "+atomicInteger.get());

        boolean compareAndSet1 = atomicInteger.compareAndSet(10, 1);

        System.out.println(compareAndSet1+" "+atomicInteger.get());
    }
}
