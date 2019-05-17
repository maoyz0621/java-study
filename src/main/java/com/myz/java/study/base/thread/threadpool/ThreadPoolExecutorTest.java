/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.base.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * @author maoyz on 2018/8/3
 * @version: v1.0
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        // 创建ThreadFactory
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build();
        /* ThreadPoolExecutor(int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue,
                        ThreadFactory threadFactory,
                        RejectedExecutionHandler handler)*/
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
                2,
                3,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }

        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));

        fixedThreadPool.shutdown();
    }
}
