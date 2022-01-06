/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步转同步
 *
 * @author maoyz0621 on 2021/12/19
 * @version v1.0
 */
public class SettableFutureTest {

    private static final Logger log = LoggerFactory.getLogger(SettableFutureTest.class);

    final static ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            60,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("settable-thread-%d").build());

    public static void main(String[] args) throws Exception {
        SettableFuture<Object> settableFuture = SettableFuture.create();

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);
        // 任务
        ListenableFuture<String> listenableFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String result = "maoyz";
                TimeUnit.SECONDS.sleep(4);
                settableFuture.set(result);
                return result;
            }
        });

        // 异步转同步
        System.out.println(settableFuture.get(3, TimeUnit.SECONDS));

        System.out.println("=========================================================");
        while (true) {
        }
    }
}