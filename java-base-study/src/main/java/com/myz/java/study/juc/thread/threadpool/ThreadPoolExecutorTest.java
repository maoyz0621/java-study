/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.juc.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * @author maoyz on 2018/8/3
 * @version: v1.0
 */
@Slf4j
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        // 创建ThreadFactory
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build();
        /* ThreadPoolExecutor(int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue,
                        ThreadFactory threadFactory,
                        RejectedExecutionHandler handler)*/
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
                2,
                4,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());


        // execute(fixedThreadPool);

        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();
            new Thread(() -> {
                submit(fixedThreadPool, j);
            }).start();


        }

        // fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        // fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        // fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        // fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        // fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        // fixedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));

         /* 通常放在execute后面。如果调用 了这个方法，一方面，表明当前线程池已不再接收新添加的线程，新添加的线程会被拒绝执行。
        另一方面，表明当所有线程执行完毕时，回收线程池的资源。
        注意，它不会马上关闭线程池！ */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdownThreadPool(fixedThreadPool, Thread.currentThread().getName())));
        // while (true) {
        //
        // }
    }

    private static void submit(ExecutorService fixedThreadPool, int j) {
        Future<String> future0 = null;
        future0 = fixedThreadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (j % 5 == 0) {
                    throw new RuntimeException("%5 call error: j = " + j);
                }
                return Thread.currentThread().getName() + " -> (0) " + j;
            }
        });

        Future<String> future1 = null;
        future1 = fixedThreadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (j % 10 == 0) {
                    throw new RuntimeException("10% call error: j = " + j);
                }
                return Thread.currentThread().getName() + " -> (1) " + j;
            }
        });

        try {
            System.out.println(Thread.currentThread().getName() + " [j] " + j + " = " + future0.get(5, TimeUnit.SECONDS));
            System.out.println(Thread.currentThread().getName() + " [j] " + j + " = " + future1.get(5, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.err.println(e);
            throw new RuntimeException("future submit error:", e);
        }
    }

    private static void execute(ExecutorService fixedThreadPool) {
        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(() -> {
                try {
                    Thread.sleep(3000);

                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    /**
     * 优雅关闭线程池
     *
     * @param threadPool
     * @param alias
     */
    private static void shutdownThreadPool(ExecutorService threadPool, String alias) {
        log.info("Start to shutdown the thead pool: {}", alias);
        /* 通常放在execute后面。如果调用 了这个方法，一方面，表明当前线程池已不再接收新添加的线程，新添加的线程会被拒绝执行。
            另一方面，表明当所有线程执行完毕时，回收线程池的资源。
            注意，它不会马上关闭线程池！ */
        threadPool.shutdown(); // 使新任务无法提交.
        try {
            // 等待未完成任务结束
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow(); // 取消当前执行的任务
                log.warn("Interrupt the worker, which may cause some task inconsistent. Please check the biz logs.");

                // 等待任务取消的响应
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.error("Thread pool can't be shutdown even with interrupting worker threads, which may cause some task inconsistent. Please check the biz logs.");
                }
            }
        } catch (InterruptedException ie) {
            /*不管当前有没有线程在执行，马上关闭线程池！这个方法要小心使用，要不可能会引起系统数据异常！*/
            threadPool.shutdownNow();
            log.error("The current server thread is interrupted when it is trying to stop the worker threads. This may leave an inconcistent state. Please check the biz logs.");

            // 保留中断状态
            Thread.currentThread().interrupt();
        }

        log.info("Finally shutdown the thead pool: {}", alias);
    }
}
