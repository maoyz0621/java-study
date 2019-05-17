/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用guava的RateLimiter做限流
 *
 * @author maoyz0621 on 19-4-14
 * @version: v1.0
 */
public class RateLimiterTest {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterTest.class);

    private static final double COUNT = 4.0;

    // 根据指定的稳定吞吐率创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询）,创建RateLimiter，一个每秒限制4个的令牌桶
    private static final RateLimiter rateLimiter = RateLimiter.create(COUNT);

    public static void main(String[] args) {
        test1();
    }

    /**
     * tryAcquire(int permits, long timeout, TimeUnit unit)
     * 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）
     */
    public static void test1() {
        final ExecutorService executorService = new ThreadPoolExecutor(
                50,
                50,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(20),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_rate_limiter_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                // 获取令牌桶中一个令牌，最多等待10秒
                // 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）
                if (rateLimiter.tryAcquire(1, 10, TimeUnit.SECONDS)) {
                    logger.debug("{}, 获取令牌桶中令牌 {}",
                            rateLimiter.acquire(2),
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
                }
            });
        }

        executorService.shutdown();
    }

    public static void test2() {

    }

}
