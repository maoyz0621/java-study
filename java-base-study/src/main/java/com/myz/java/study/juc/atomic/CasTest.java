/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 比较并交换 CompareAndSwap
 *
 * @author maoyz0621 on 19-10-31
 * @version: v1.0
 */
public class CasTest {

    @Test
    public void testCompareAndSet() {
        AtomicInteger atomicInteger = new AtomicInteger(10);

        boolean compareAndSet = atomicInteger.compareAndSet(10, 1);
        System.out.println(compareAndSet + " " + atomicInteger.get());

        boolean compareAndSet1 = atomicInteger.compareAndSet(10, 1);
        System.out.println(compareAndSet1 + " " + atomicInteger.get());

    }

    @Test
    public void testAdd() {
        AtomicInteger atomicInteger = new AtomicInteger(10);


        // public final int getAndAddInt(Object var1, long var2, int var4) {
        //     int var5;
        //     do {
        //         var5 = this.getIntVolatile(var1, var2);
        //     } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        //
        //     return var5;
        // }
        //  其中 var1 表示对象本身
        //  var2 对象值的引用地址
        //  var4 需要变动的值
        //  var5 找出 var2 在 var1 主内存中真实的值
        //  使用该对象当前值与var5比较, 相同更新var4 + var5
        //  期待是10, true就update, false不update
        int andAdd = atomicInteger.getAndAdd(10);
        System.out.println("getAndAdd()" + andAdd);

        System.out.println("Now is " + atomicInteger.get());

        int addAndGet = atomicInteger.addAndGet(12);
        System.out.println("addAndGet()" + addAndGet);
    }


    @Test
    public void testGet() {
        AtomicInteger atomicInteger = new AtomicInteger(10);

        // public final int getAndSetInt(Object var1, long var2, int var4) {
        //     int var5;
        //     do {
        //         var5 = this.getIntVolatile(var1, var2);
        //     } while(!this.compareAndSwapInt(var1, var2, var5, var4));
        //
        //     return var5;
        // }
        // get -> set
        int andSet = atomicInteger.getAndSet(2);
        System.out.println(andSet);
        System.out.println(atomicInteger.get());
    }
}
