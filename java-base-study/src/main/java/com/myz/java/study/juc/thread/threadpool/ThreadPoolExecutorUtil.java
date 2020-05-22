/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-21 11:20  Inc. All rights reserved.
 */
package com.myz.java.study.juc.thread.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程池操作工具类，参考org.apache.dubbo.common.utils.ExecutorUtil
 *
 * @author maoyz
 */
@Slf4j
public class ThreadPoolExecutorUtil {

    private static final ThreadPoolExecutor SHUTDOWN_EXECUTOR = new ThreadPoolExecutor(0, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            new NamedThreadFactory("Close-ExecutorService", true));


    public static boolean isTerminated(Executor executor) {
        if (executor instanceof ExecutorService) {
            return ((ExecutorService) executor).isTerminated();
        }
        return false;
    }

    /**
     * 优雅关闭线程池
     */
    public static void gracefulShutdown(Executor executor, int timeout) {
        if (!(executor instanceof ExecutorService) || isTerminated(executor)) {
            return;
        }

        /* 通常放在execute后面。如果调用 了这个方法，一方面，表明当前线程池已不再接收新添加的线程，新添加的线程会被拒绝执行。
            另一方面，表明当所有线程执行完毕时，回收线程池的资源。
            注意，它不会马上关闭线程池！ */
        final ExecutorService executorService = (ExecutorService) executor;

        try {
            // 使新任务无法提交.
            executorService.shutdown();
        } catch (SecurityException | NullPointerException e) {
            return;
        }

        try {
            // 等待未完成任务结束
            if (!executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // 取消当前执行的任务
                log.warn("Interrupt the worker, which may cause some task inconsistent. Please check the biz logs.");
            }
        } catch (InterruptedException ie) {
            /* 不管当前有没有线程在执行，马上关闭线程池！这个方法要小心使用，要不可能会引起系统数据异常！*/
            executorService.shutdownNow();
            log.error("The current server thread is interrupted when it is trying to stop the worker threads. This may leave an inconcistent state. Please check the biz logs.");

            // 保留中断状态
            Thread.currentThread().interrupt();
        }

        if (!isTerminated(executorService)) {
            newThreadToCloseExecutor(executorService);
        }
    }


    public static void shutdownNow(Executor executor, final int timeout) {
        if (!(executor instanceof ExecutorService) || isTerminated(executor)) {
            return;
        }

        final ExecutorService executorService = (ExecutorService) executor;
        try {
            executorService.shutdownNow();
        } catch (SecurityException | NullPointerException ex2) {
            return;
        }

        try {
            executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        if (!isTerminated(executorService)) {
            newThreadToCloseExecutor(executorService);
        }
    }

    private static void newThreadToCloseExecutor(final ExecutorService executorService) {
        if (!isTerminated(executorService)) {
            SHUTDOWN_EXECUTOR.execute(() -> {
                try {
                    for (int i = 0; i < 1000; i++) {
                        executorService.shutdownNow();
                        if (executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
    }

}