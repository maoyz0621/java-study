package com.myz.java.study.juc.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 * 实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用
 *
 * @author maoyz on 18-1-11.
 */
public class CyclicBarrierDemo {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierDemo.class);

    public static void main(String[] args) {
        final int count = 5;
        // 定义n个并行执行
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> logger.debug("当前线程 = {}", Thread.currentThread().getName()));

        // 当线程数和cyclicBarrier相同时，可体现并行任务
        for (int i = 0; i < count; i++) {
            new Thread(new Writer(cyclicBarrier)).start();
        }

        // 模拟重复利用
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("循环利用");

        // 再次使用CyclicBarrier
        for (int i = 0; i < count; i++) {
            new Thread(new Writer(cyclicBarrier)).start();
        }
    }

    static class Writer implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            logger.debug(Thread.currentThread().getName() + " 正在执行写操作...");
            try {
                Thread.sleep(2000);
                logger.debug(Thread.currentThread().getName() + " 正在写...");
                // 执行等待,断点，等待所有完成
                // cyclicBarrier.await(10, TimeUnit.SECONDS);
                cyclicBarrier.await();
                // logger.debug(" {} ", cyclicBarrier.getParties());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.debug(Thread.currentThread().getName() + " 完成写操作");
        }
    }
}
