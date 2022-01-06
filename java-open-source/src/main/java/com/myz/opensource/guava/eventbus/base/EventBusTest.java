/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.eventbus.base;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 2021/12/25
 * @version v1.0
 */
public class EventBusTest {

    @Test
    public void test1() {
        // 同步使用当前线程
        final EventBus eventBus = new EventBus();
        // 注册监听者
        eventBus.register(new MultipleEventListener());
        eventBus.register(new SendSmsService());
        eventBus.register(new SendCouponService());

        // 发送事件
        eventBus.post(new UserCreateEvent("abc"));
        eventBus.post(new UserCreateEvent("xyz"));

        eventBus.post(2);
        eventBus.post(12);
        eventBus.post(true);
    }

    @Test
    public void test2() {
        final ExecutorService executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                50,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(20),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_AsyncEventBus_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
        // 异步使用
        final EventBus eventBus = new AsyncEventBus(executorService);
        
        // 注册监听者
        eventBus.register(new MultipleEventListener());
        eventBus.register(new SendSmsService());
        eventBus.register(new SendCouponService());

        // 发送事件
        eventBus.post(new UserCreateEvent("abc"));
        eventBus.post(new UserCreateEvent("xyz"));

        eventBus.post(2);
        eventBus.post(12);
        eventBus.post(true);

        // PerThreadQueuedDispatcher 每个线程一个队列派发
        // LegacyAsyncDispatcher 异步派发使用(异步AsyncEventBus默认使用)
        // ImmediateDispatcher 立即派发(无队列)
    }

}