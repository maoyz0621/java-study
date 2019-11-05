/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.atomic.aba;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 加版本号的原子引用 AtomicStampedReference
 *
 * @author maoyz0621 on 19-11-5
 * @version: v1.0
 */
public class AtomicStampedReferenceTest {

    static final ExecutorService fixedThreadPool = new ThreadPoolExecutor(100, 150, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        AtomicReference<Integer> reference = new AtomicReference<>(10);

        fixedThreadPool.execute(() -> {
            reference.compareAndSet(10, 100);
            reference.compareAndSet(100, 10);
        });

        fixedThreadPool.execute(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(10, 101) + " -> now AtomicReference value = " + reference.get());
        });


        System.out.println("\r\n=====================================\r\n");


        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(10, 1);

        fixedThreadPool.execute(() -> {
            int stamp = stampedReference.getStamp();

            // 休眠1s
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": " + stampedReference.compareAndSet(10, 100, stamp, stamp + 1) + ", stamp = " + stampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + ": " + stampedReference.compareAndSet(100, 10, stampedReference.getStamp(), stampedReference.getStamp() + 1)
                    + ", stamp = " + stampedReference.getStamp());
        });


        fixedThreadPool.execute(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + ": " + ", stamp = " + stamp);


            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 此时期望stamp=stamp
            System.out.println(Thread.currentThread().getName() + ": " + stampedReference.compareAndSet(10, 101, stamp, stamp + 1)
                    + ", stamp = " + stampedReference.getStamp()
                    + " -> now AtomicStampedReference value = " + stampedReference.getReference());
        });

        fixedThreadPool.shutdown();
    }
}
