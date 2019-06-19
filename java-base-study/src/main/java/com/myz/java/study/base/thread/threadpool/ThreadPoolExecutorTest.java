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

         /* 通常放在execute后面。如果调用 了这个方法，一方面，表明当前线程池已不再接收新添加的线程，新添加的线程会被拒绝执行。
        另一方面，表明当所有线程执行完毕时，回收线程池的资源。
        注意，它不会马上关闭线程池！ */
        fixedThreadPool.shutdown();

        /*不管当前有没有线程在执行，马上关闭线程池！这个方法要小心使用，要不可能会引起系统数据异常！*/
        // fixedThreadPool.shutdownNow();
    }
}
