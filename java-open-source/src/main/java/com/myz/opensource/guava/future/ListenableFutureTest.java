/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 2021/12/19
 * @version v1.0
 */
public class ListenableFutureTest {

    private static final Logger log = LoggerFactory.getLogger(ListenableFutureTest.class);

    final static ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            60,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("ListeningExecutor-thread-%d").build());

    final static ThreadPoolExecutor executor0 = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            60,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("Callback-thread-%d").build());

    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);
        // 任务1
        ListenableFuture<String> listenableFuture1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("listenableFuture1 = maoyz");
                return "maoyz";
            }
        });

        // 添加回调参数，使用另一个线程池
        Futures.addCallback(listenableFuture1, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                log.info("String = {}", result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, executor0);
        System.out.println("==================== 任务1执行完成 =======================");


        // 任务2
        ListenableFuture<Boolean> listenableFuture2 = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                log.info("listenableFuture2 = true");
                return true;
            }
        });

        Futures.addCallback(listenableFuture2, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean result) {
                log.info("Boolean = {}", result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, executor0);
        System.out.println("==================== 任务2执行完成 =======================");

        ListenableFuture<Integer> listenableFuture3 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("listenableFuture3 = 1");
                return 1;
            }
        });

        Futures.addCallback(listenableFuture3, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                log.info("get listenable future‘s result with callback = {}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, executor0);
        System.out.println("==================== 任务3执行完成 =======================");

        // 任务1
        ListenableFuture<String> listenableFuture4 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int i = 1 / 0;
                return "maoyz";
            }
        });

        // 添加回调参数，使用另一个线程池
        Futures.addCallback(listenableFuture4, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                log.info("String = {}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("listenableFuture4 failure:", t);
            }
        }, executor0);
        System.out.println("==================== 任务4执行完成 =======================");

        while (true) {
        }
    }
}