package com.myz.java.study.juc.thread.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1. 当所有线程共享同一个对象时，操作同一个对象
 * 2. 当线程专享new新的对象，独享操作对象
 *
 * @author maoyz
 */
public class ThreadUseObject {

    private int time = 0;

    private static int size = 0;

    public void retry() {
        time++;
        while (time < 3) {
            System.out.println(Thread.currentThread().getName() + " -> " + this + " -> " + time);
            retry();
        }
    }

    public static void retrys() {
        size++;
        while (size < 3) {
            System.out.println(Thread.currentThread().getName() + " -> " + ThreadUseObject.class.hashCode() + " -> " + size);
            retrys();
        }
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
                100,
                150,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        /* 所有线程共享同一个对象 */
        // main0(fixedThreadPool);

        /* 每个线程专享独有对象 */
        // main1(fixedThreadPool);

        /* static */
        main2(fixedThreadPool);

    }

    private static void main1(ExecutorService fixedThreadPool) {
        for (int i = 0; i < 200; i++) {
            fixedThreadPool.execute(() -> {
                ThreadUseObject threadUseObject0 = new ThreadUseObject();
                threadUseObject0.retry();
            });
        }
    }

    private static void main0(ExecutorService fixedThreadPool) {
        ThreadUseObject threadUseObject = new ThreadUseObject();
        for (int i = 0; i < 200; i++) {
            fixedThreadPool.execute(() -> {
                threadUseObject.retry();
            });
        }
    }

    private static void main2(ExecutorService fixedThreadPool) {
        for (int i = 0; i < 200; i++) {
            fixedThreadPool.execute(() -> {
                ThreadUseObject.retrys();
            });
        }
    }
}
