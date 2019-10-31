package com.myz.java.study.base.thread.volatile1;

import lombok.extern.slf4j.Slf4j;

/**
 * 关键字volatile不能保证原子性，存在线程安全问题
 *
 * @author maoyz on 18-1-7.
 */
@Slf4j
public class VolatileNotAtomic implements Runnable {

    /**
     * 使用volatile修饰
     */
    private volatile int count = 0;

    private int size = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            size++;
        }
        log.debug(Thread.currentThread().getName() + "-count =  " + count);
        log.debug(Thread.currentThread().getName() + "-size =  " + size);
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
