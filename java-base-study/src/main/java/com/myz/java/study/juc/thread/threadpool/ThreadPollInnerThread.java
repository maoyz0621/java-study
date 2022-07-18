/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.juc.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 2022/6/21
 * @version v1.0
 */
public class ThreadPollInnerThread {

    private static final int BLOCKING_QUEUE_CAPACITY = 1;
    private final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("async-tool-pool-%d").build();
    private final ThreadPoolExecutor commonAsyncExecutor =
            new ThreadPoolExecutor(1, 2,
                    10L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(BLOCKING_QUEUE_CAPACITY), namedThreadFactory);

    @Test
    public void test1() {
        commonAsyncExecutor.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("name1 = " + Thread.currentThread().getName());
        });

        commonAsyncExecutor.execute(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("name2 = " + Thread.currentThread().getName());
        });

        while (true) {
        }
    }

    /**
     * 线程池中的线程执行完任务之后释放线程
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        Future<?> submit1 = commonAsyncExecutor.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("name1 = " + Thread.currentThread().getName());
            return 1;
        });

        Future<?> submit2 = commonAsyncExecutor.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("name2 = " + Thread.currentThread().getName());
            return "a";
        });

        Future<?> submit3 = commonAsyncExecutor.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("name3 = " + Thread.currentThread().getName());
            return "a1";
        });

        // Object o2 = submit2.get();
        // System.out.println("o2 = " + o2);
        // Object o1 = submit1.get();
        // System.out.println("o1 = " + o1);

        while (true) {
        }
    }
}