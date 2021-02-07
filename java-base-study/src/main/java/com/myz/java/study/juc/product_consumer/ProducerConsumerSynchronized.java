/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.juc.product_consumer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者 关键字synchronized
 * 要求：一个线程+1,一个线程-1
 * 1 高内聚低耦合前提下，线程操作资源类
 * 2 判断/工作/通知
 * 3 防止虚假唤醒
 *
 * @author maoyz0621 on 20-5-12
 * @version v1.0
 */
public class ProducerConsumerSynchronized {
    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            100,
            150,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        Worker worker = new Worker();
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

        fixedThreadPool.shutdown();
    }
}

class Worker {

    private int count;

    public void increment() throws InterruptedException {
        synchronized (this) {
            while (count != 0) {
                this.wait();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " => " + count);
            this.notifyAll();
        }
    }

    public synchronized void decrement() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " => " + count);
        this.notifyAll();
    }

}