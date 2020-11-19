package com.myz.java.study.juc.thread.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一般ThreadLocal使用static修饰
 *
 * @author maoyz
 */
public class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

    public static int get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return threadId.get();
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(100, 150, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 1000; i++) {
            fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " = " + ThreadId.get()));
        }
    }
}
