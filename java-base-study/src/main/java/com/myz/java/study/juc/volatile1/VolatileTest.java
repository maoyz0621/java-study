/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.volatile1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maoyz0621 on 19-3-25
 * @version: v1.0
 */
public class VolatileTest {

    /**
     * 使用volatile 保证了可见性，其他线程也可以查看更新之后的值
     */
    private volatile int inv = 0;
    private int ins = 0;
    private int inl = 0;

    /**
     * 定义锁
     */
    private Lock lock = new ReentrantLock();

    public void invIncrease() {
        inv++;
    }

    /**
     * synchronized修饰
     */
    public synchronized void insIncrease() {
        ins++;
    }

    /**
     * Lock
     */
    public void inlIncrease() {
        lock.lock();
        try {
            inl++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用 volatile 是无法保证 原子性的
     * 因为 自增操作不是原子性操作
     */
    public static void volatileTs() {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.invIncrease();
                }
            }).start();
        }
        // 数据小于 10000  例如:9303,9068
        System.out.println(test.inv);
    }

    /**
     * 使用 volatile 是无法保证 原子性的
     * 因为 自增操作不是原子性操作
     */
    public static void syncTs() {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.insIncrease();
                }
            }).start();
        }

        // 9000
        System.out.println(test.ins);
    }

    /**
     * 使用 volatile 是无法保证 原子性的
     * 因为 自增操作不是原子性操作
     */
    public static void lockTs() {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.inlIncrease();
                }
            }).start();
        }
        // 9000
        System.out.println(test.inl);

    }

    public static void main(String[] args) {
        // volatileTs();
        // syncTs();
        lockTs();
    }

}
