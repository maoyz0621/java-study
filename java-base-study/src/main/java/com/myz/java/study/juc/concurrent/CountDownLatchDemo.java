package com.myz.java.study.juc.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch(门栓)
 * 　　计数器
 * 使一个线程等待其他线程完成各自的工作后再执行
 *
 * @author maoyz on 18-1-9.
 */
public class CountDownLatchDemo {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchDemo.class);

    public static void main(String[] args) throws Exception {
        // main1();
        main0();
    }

    private static void main1() {
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

    /**
     * @throws Exception
     */
    private static void main0() {
        final int count = 50;
        List<Integer> list = new CopyOnWriteArrayList<>();
        List<Integer> list1 = new CopyOnWriteArrayList<>();
        // 20线程
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < count; i++) {
            int j = i;
            list.add(j);
        }
        // 定义计数器
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());

        try {
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Integer next = (Integer) iterator.next();
                executorService.execute(() -> {
                    try {
                        Thread.sleep(3000);
                        if (next.equals(20)) {
                            int j = 1 / 0;
                        }
                        list1.add(next);
                    } catch (Exception e) {
                        logger.error("{}", e);
                        throw new RuntimeException(e);
                        // countDownLatch.countDown();
                    }
                    logger.info("{}", countDownLatch.getCount());
                    countDownLatch.countDown();
                });
            }
        } catch (Exception e) {
            countDownLatch.countDown();
        } finally {
            // 等待, 阻断
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("{}", list1);
            logger.info("{}", list1.size());
            executorService.shutdown();
        }
    }

    public static void main6() throws InterruptedException {
        final int count = 30;
        final Semaphore semaphore = new Semaphore(10, false);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                atomicInteger.addAndGet(1);
                countDownLatch.countDown();
                try {
                    /* 从信号量尝试获取一个许可，如果无可用许可，直接返回false，不会阻塞 */
                    // semaphore.tryAcquire();

                    /* 从信号量获取一个许可，如果无可用许可前 将一直阻塞等待 */
                    semaphore.acquire();
                    System.out.println("------------" + Thread.currentThread().getName() + " : " + semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000));

                    int j = 1 / 0;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
        }

        countDownLatch.await();
        System.out.println(atomicInteger.get());
        executorService.shutdown();
    }


}
