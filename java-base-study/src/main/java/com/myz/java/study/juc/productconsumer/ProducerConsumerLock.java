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
 * 生产者消费者 Lock和Condition
 * 要求：一个线程+1,一个线程-1
 * 1 高内聚低耦合前提下，线程操作资源类
 * 2 判断/工作/通知
 * 3 防止虚假唤醒
 *
 * @author maoyz0621 on 20-5-12
 * @version v1.0
 */
public class ProducerConsumerLock {
    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            100,
            150,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        // lock1();
        // lock2();
        lock3();


        fixedThreadPool.shutdown();
    }

    private static void lock3() {
        Lock lock = new ReentrantLock();
        WorkerLock worker0 = new WorkerLock(lock);
        WorkerLock worker1 = new WorkerLock(lock);
        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        worker0.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        worker1.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void lock2() {
        WorkerLock worker0 = new WorkerLock();
        WorkerLock worker1 = new WorkerLock();
        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        worker0.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        worker1.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void lock1() {
        WorkerLock worker = new WorkerLock();
        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        worker.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int j = 0; j < 3; j++) {
            fixedThreadPool.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        worker.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

class WorkerLock {

    private int count;
    private Lock lock;
    private Condition condition;

    public WorkerLock() {
    }

    public WorkerLock(Lock lock) {
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (count != 0) {
                condition.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " => " + count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + " => " + count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

}