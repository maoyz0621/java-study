package com.myz.java.study.base.thread.volatile1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关键字volatile不能保证原子性，存在线程安全问题
 *
 * @author maoyz on 18-1-7.
 */
public class VolatileNotAtomic implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(VolatileNotAtomic.class);

    /**
     * 使用volatile修饰
     */
    private volatile int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        logger.debug(Thread.currentThread().getName() + " : " + count);
    }
}

class Demo1 {

    public static void main(String[] args) {
        VolatileNotAtomic volatileNotAtomic = new VolatileNotAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread(volatileNotAtomic).start();
        }
    }
}
