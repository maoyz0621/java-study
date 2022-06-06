/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用 TtlExecutors.getTtlExecutor(fixedThreadPool)
 *
 * @author maoyz0621 on 2022/5/25
 * @version v1.0
 */
public class InheritableThreadLocalWithTtlPoolTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InheritableThreadLocalWithTtlPoolTest.class);
    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            4,
            4,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(5),
            new ThreadFactoryBuilder().setNameFormat("ttl_thread_pool_%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        final ThreadLocal<Integer> holder = new InheritableThreadLocal<>();
        // 替换TTL包装的线程池
        Executor ttlExecutor = TtlExecutors.getTtlExecutor(fixedThreadPool);
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        list.forEach(i -> {
            holder.set(i);
            CompletableFuture.supplyAsync(() -> {
                LOGGER.info("子线程id={}，set_content={}, get_content={}", Thread.currentThread().getName(), i, holder.get());
                return null;
            }, ttlExecutor);
        });
        // 子线程id=ttl_thread_pool_0，set_content=1, get_content=1
        // 子线程id=ttl_thread_pool_2，set_content=3, get_content=3
        // 子线程id=ttl_thread_pool_1，set_content=2, get_content=2
        // 子线程id=ttl_thread_pool_0，set_content=5, get_content=1
        // 子线程id=ttl_thread_pool_3，set_content=4, get_content=4

    }
}