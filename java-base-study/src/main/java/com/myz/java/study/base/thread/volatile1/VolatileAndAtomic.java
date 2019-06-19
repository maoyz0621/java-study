package com.myz.java.study.base.thread.volatile1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 关键字volatile不能保证原子性，存在线程安全问题
 *
 * @author maoyz on 18-1-7.
 */
public class VolatileAndAtomic implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(VolatileAndAtomic.class);

    /**
     * 使用volatile修饰原子类
     */
    private volatile AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count.getAndIncrement();
        }
        logger.debug(Thread.currentThread().getName() + " : " + count.intValue());
    }
}

class Demo2 {

    public static void main(String[] args) {
        VolatileNotAtomic volatileNotAtomic = new VolatileNotAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread(volatileNotAtomic).start();
        }
    }
}
