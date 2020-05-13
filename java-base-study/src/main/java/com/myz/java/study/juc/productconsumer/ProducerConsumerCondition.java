/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.juc.productconsumer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程A -> B -> C
 * A 5次
 * B 10次
 * C 15次
 * 循环
 *
 * @author maoyz0621 on 20-5-13
 * @version v1.0
 */
public class ProducerConsumerCondition {
    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            100,
            150,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        WorkCondition work = new WorkCondition();
        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    work.printA();
                }
            });
        }

        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    work.printB();
                }
            });
        }

        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    work.printC();
                }
            });
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> fixedThreadPool.shutdown()));
    }
}

class WorkCondition {
    /**
     * 打印A B C的标志位
     */
    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;
    /**
     * 标志位
     */
    private volatile int num = A;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (num != A) {
                // A等待
                conditionA.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " A " + i);
            }
            num = B;
            // 唤醒B
            conditionB.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (num != B) {
                // B等待
                conditionB.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " B " + i);
            }
            num = C;
            // 唤醒C
            conditionC.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != C) {
                // C等待
                conditionC.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " C " + i);
            }
            num = A;
            // 唤醒A
            conditionA.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}