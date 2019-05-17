package com.myz.java.study.base.thread.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch(门栓)
 * 　　计数器
 * 使一个线程等待其他线程完成各自的工作后再执行
 *
 * @author maoyz on 18-1-9.
 */
public class CountDownLatchDemo {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchDemo.class);

    public static void main(String[] args) {
        // final int count = 5;
        final int count = 10;
        // 定义一个计数器
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        // 子线程
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                synchronized (CountDownLatchDemo.class) {
                    logger.debug("子线程执行开始...." + Thread.currentThread().getName());
                    // 递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
                    countDownLatch.countDown();
                    logger.debug(Thread.currentThread().getName() + "此时计数器为:" + countDownLatch.getCount());
                    logger.debug("子线程执行完毕...." + Thread.currentThread().getName());

                    logger.info("------------------");
                }
            }).start();
        }

        try {
            // 如果不为0的时候，一直等待,可以设置等待时间，防止无限等待下去
            countDownLatch.await(10, TimeUnit.SECONDS);
            logger.info("此时主线程计数器为:" + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保在程序的最后执行
        logger.debug("主线程执行...." + Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) {
            logger.debug("{}", i);
        }

        logger.info("finish ...");
    }
}
