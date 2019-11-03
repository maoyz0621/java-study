package com.myz.java.study.base.thread.volatile1;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 关键字volatile不能保证原子性，存在线程安全问题
 *
 * @author maoyz on 18-1-7.
 */
@Slf4j
public class VolatileAndAtomic implements Runnable {

    /**
     * 使用atomic原子类
     */
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.getAndIncrement();
        }
        log.debug(Thread.currentThread().getName() + " : " + count.intValue());
    }

    public static void main(String[] args) {
        VolatileAndAtomic volatileAndAtomic = new VolatileAndAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread(volatileAndAtomic).start();
        }
    }

}