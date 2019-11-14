/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author maoyz0621 on 19-11-13
 * @version: v1.0
 */
@Slf4j
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        log.debug("{} start lock", Thread.currentThread().getName());

        // 循环尝试获取
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        log.debug("{} start unlock", Thread.currentThread().getName());
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(100, 150, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        SpinLock spinLock = new SpinLock();

        fixedThreadPool.execute(() -> {
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLock.unLock();
        });


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fixedThreadPool.execute(() -> {
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unLock();
        });


        fixedThreadPool.shutdown();

    }
}
