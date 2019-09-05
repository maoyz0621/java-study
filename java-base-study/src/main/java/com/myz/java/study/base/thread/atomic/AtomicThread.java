package com.myz.java.study.base.thread.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用java1.5并发包中原子类atomic,保证最终一致性
 *
 * @author maoyz on 18-1-7.
 */
public class AtomicThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AtomicThread.class);

    /**
     * 原子类:AtomicInteger,保证了数据的原子性
     */
    private AtomicInteger count = new AtomicInteger(0);
    private int count1;

    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {
            // 数据自增,  getAndIncrement(),替代count++
            count.incrementAndGet();

            count1++;
        }

        logger.debug(Thread.currentThread().getName() + " AtomicInteger : " + count.intValue());
        logger.debug(Thread.currentThread().getName() + " int :           " + count1);
    }
}


class AtomicTest {

    public static void main(String[] args) {

        AtomicThread atomicThread = new AtomicThread();
        // 最终 原子类count = 100000
        for (int i = 0; i < 10; i++) {
            new Thread(atomicThread).start();
        }
    }
}
