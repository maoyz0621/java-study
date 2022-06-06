/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.juc.thread.threadlocal;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 2022/5/25
 * @version v1.0
 */
public class InheritableThreadLocalWithPoolTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InheritableThreadLocalWithPoolTest.class);
    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            4,
            4,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(5),
            new ThreadFactoryBuilder().setNameFormat("itl_thread_pool_%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        ThreadLocal<Integer> holder = new InheritableThreadLocal<>();
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        list.forEach(i -> {
            holder.set(i);
            LOGGER.info("主线程id={}，set_content={}, get_content={}", Thread.currentThread().getName(), i, holder.get());
            CompletableFuture.supplyAsync(() -> {
                LOGGER.info("子线程id={}，set_content={}, get_content={}", Thread.currentThread().getName(), i, holder.get());
                return null;
            }, fixedThreadPool);
        });
        // 子线程id=itl_thread_pool_0，set_content=1, get_content=1
        // 子线程id=itl_thread_pool_3，set_content=4, get_content=4
        // 子线程id=itl_thread_pool_2，set_content=3, get_content=3
        // 子线程id=itl_thread_pool_0，set_content=5, get_content=1
        // 子线程id=itl_thread_pool_1，set_content=2, get_content=2

        // 使用TtlExecutors.getTtlExecutor(fixedThreadPool) TTL提供的包装线程池

    }
}