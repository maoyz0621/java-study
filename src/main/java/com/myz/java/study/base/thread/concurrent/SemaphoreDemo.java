package com.myz.java.study.base.thread.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Semaphore　信号量
 *
 * @author maoyz on 18-1-10.
 */
public class SemaphoreDemo {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreDemo.class);

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("semaphore_thread_pool_%d").build();
        ExecutorService threadPool = new ThreadPoolExecutor(
                5,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        final int count = 3;
        // 信号量，允许的资源访问
        final Semaphore semaphore = new Semaphore(count, true);

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
            // 当前可用的许可数
            int mount = semaphore.availablePermits();
            // logger.debug("当前可用的许可数 = {}", mount);
            //
            // if (mount > 0) {
            //     logger.debug(Thread.currentThread().getName() + "欧耶....." + ":" + mount);
            // } else {
            //     logger.warn(Thread.currentThread().getName() + "我了个去....." + ":" + mount);
            // }

            try {
                //　获得许可
                semaphore.acquire();
                logger.debug(Thread.currentThread().getName() + "运行：" + num);
                Thread.sleep(2000);
                logger.debug(Thread.currentThread().getName() + "释放....." + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //　释放资源
                semaphore.release();
            }
        }
    }

}
