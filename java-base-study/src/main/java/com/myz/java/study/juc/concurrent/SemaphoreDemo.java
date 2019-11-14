package com.myz.java.study.juc.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Semaphore　信号量 ,用来控制同时访问特定资源的线程数量，通过协调各个线程以保证合理地使用公共资源
 * <p>
 * 每次acquire信号成功后，Semaphore可用的信号量就会减一，同样release成功之后，Semaphore可用信号量的数目会加一，
 * 如果信号量的数量减为0，acquire调用就会阻塞，直到release调用释放信号后，acquire才会获得信号返回。
 * <p>
 * 主要用于多个共享资源的互斥使用; 并发线程数的控制
 *
 * @author maoyz on 18-1-10.
 */
public class SemaphoreDemo {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreDemo.class);

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(100, 150, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(1024),
                new ThreadFactoryBuilder().setNameFormat("semaphore_thread_pool_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        final int count = 3;
        // 信号量，允许的资源访问
        final Semaphore semaphore = new Semaphore(count, false);

        final int times = 10;
        for (int i = 0; i < times; i++) {
            int temp = i;
            threadPool.execute(new MyThread(semaphore, temp));
        }
        threadPool.shutdown();

       /* for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(new MyThread(semaphore, temp)).start();
        }*/
    }

    static class MyThread implements Runnable {

        private Semaphore semaphore;

        private int num;

        public MyThread(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {
            // 当前可用的许可数 Semaphore设置的阈值 count
            int mount = semaphore.availablePermits();
            logger.debug("当前可用的许可数 = {}", mount);

            try {
                // semaphore.tryAcquire(){}
                //　获得许可
                semaphore.acquire();
                int permits = semaphore.availablePermits();
                logger.debug("***************** 当前可用的许可数 = {}", permits);

                logger.debug(Thread.currentThread().getName() + " 运行：" + num);
                Thread.sleep(2000);
                logger.debug(Thread.currentThread().getName() + " 释放..... " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //　释放资源
                semaphore.release();
            }
        }
    }

}
