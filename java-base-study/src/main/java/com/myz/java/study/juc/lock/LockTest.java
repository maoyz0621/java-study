/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-27 14:12 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.juc.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maoyz
 */
public class LockTest {

    // 创建ThreadFactory
    static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build();
    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            10,
            50,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        // main0();
        main1();
        // main2();
        // main3();
    }

    /**
     * ReentrantLock
     */
    private static void main0() {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(reentrantLockTest::reentrant);
        }
        fixedThreadPool.shutdown();
    }

    /**
     * ReentrantReadWriteLock
     */
    private static void main1() {
        ReentrantReadWriteLockTest task = new ReentrantReadWriteLockTest();
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> task.read());
        }

        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> task.write());
        }

        fixedThreadPool.shutdown();
        System.out.println("************** 运行结束 ****************");
    }

    private static void main2() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            fixedThreadPool.execute(() -> treeMap.put(temp + "", temp));
        }

        for (int i = 0; i < 10; i++) {
            int temp = i;
            fixedThreadPool.execute(() -> System.out.println(treeMap.get(temp + "")));
        }

        fixedThreadPool.shutdown();
        System.out.println("************** 运行结束 ****************");
    }

    Lock lock = new ReentrantLock();
    static int a = 0;

    private static void main3() {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    lockTest.lock0();
                }
            });

        }
    }

    public void lock0() {
        System.out.println(lock);
        // lock.lock();
        // System.out.println(a++);
        try {
            // java.lang.IllegalMonitorStateException
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(1000);
                System.out.println(a++);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
