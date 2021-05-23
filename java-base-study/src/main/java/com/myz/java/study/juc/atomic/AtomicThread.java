package com.myz.java.study.juc.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用java1.5并发包中原子类atomic, 保证最终一致性
 *
 * @author maoyz on 18-1-7.
 */
public class AtomicThread implements Runnable {
    private Logger log = LoggerFactory.getLogger(AtomicThread.class);
    private static final int size = 10000;
    /**
     * 原子类:AtomicInteger,保证了数据的原子性
     */
    private AtomicInteger count = new AtomicInteger(0);
    private int count1;
    private boolean need = false;

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            // 数据自增,  getAndIncrement(),替代count++
            count.incrementAndGet();
            count1++;
        }
        log.debug(Thread.currentThread().getName() + " AtomicInteger : " + count.intValue());
        log.debug(Thread.currentThread().getName() + " int :           " + count1);
    }

    public AtomicInteger getCount() {
        return count;
    }

    public int getCount1() {
        return count1;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicThread atomicThread = new AtomicThread();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        // 最终 原子类count = 100000
        int i = 0;
        while (i < 10) {
            i++;
            new Thread(atomicThread).start();
            countDownLatch.countDown();
        }
        countDownLatch.await();
        System.out.println(atomicThread.getCount());
        System.out.println(atomicThread.getCount1());
    }
}
