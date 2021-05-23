/**
 * Copyright 2021 Inc.
 **/
package com.myz.java.study.juc.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 2021/5/23
 * @version v1.0
 */
public class ThreadPoolParam {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.execute(new Task(1));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());

        System.out.println("-----------------------------------------");

        executor.execute(new Task(2));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());

        System.out.println("-----------------------------------------");

        executor.execute(new Task(3));
        executor.execute(new Task(4));
        executor.execute(new Task(5));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());

        System.out.println("-----------------------------------------");


        executor.execute(new Task(6));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());

        System.out.println("-----------------------------------------");

        executor.execute(new Task(7));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());

        System.out.println("-----------------------------------------");

        executor.execute(new Task(8));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());

        System.out.println("-----------------------------------------");

        executor.execute(new Task(9));
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getQueue());
    }

    private static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // System.out.println("Task " + i + " End");
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Task{");
            sb.append("i=").append(i);
            sb.append('}');
            return sb.toString();
        }
    }
}
