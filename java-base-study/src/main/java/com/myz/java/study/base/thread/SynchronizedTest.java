/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * synchronized 属于可重入锁
 *
 * @author maoyz0621 on 19-11-6
 * @version: v1.0
 */
public class SynchronizedTest {

    private static class Test {

        /**
         * synchronized修饰的方法, 调用synchronized修饰的m2()
         */
        public synchronized void m1() {
            System.out.println(Thread.currentThread().getName() + " m1()");
            m2();
        }

        public synchronized void m2() {
            System.out.println(Thread.currentThread().getName() + " m2()");
        }

    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(100, 150, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        Test test = new Test();

        fixedThreadPool.execute(test::m1);

        fixedThreadPool.execute(test::m1);

        fixedThreadPool.shutdown();
    }

}
