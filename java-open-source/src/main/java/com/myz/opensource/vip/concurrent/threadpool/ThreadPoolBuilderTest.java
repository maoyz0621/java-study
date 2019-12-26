/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.vip.concurrent.threadpool;

import com.vip.vjtools.vjkit.concurrent.threadpool.QueuableCachedThreadPool;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolBuilder;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;
import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 19-4-26
 * @version: v1.0
 */
public class ThreadPoolBuilderTest {

    @Test
    public void cachedPool() {
        ThreadPoolExecutor poolExecutor = ThreadPoolBuilder.cachedPool().build();
        // 0
        System.out.println(poolExecutor.getCorePoolSize());
        System.out.println(poolExecutor.getMaximumPoolSize());
        // 10s
        System.out.println(poolExecutor.getKeepAliveTime(TimeUnit.SECONDS));
        // SynchronousQueue
        System.out.println(poolExecutor.getQueue().getClass());
        poolExecutor.shutdown();

        ///////////////////////////// cachedPool 设置 ////////////////////////////
        ThreadPoolExecutor poolExecutor1 = ThreadPoolBuilder.cachedPool()
                .setMinSize(10).setMaxSize(100).setKeepAliveSecs(20).setThreadNamePrefix("cache_pool_")
                .build();
        // 10
        System.out.println(poolExecutor1.getCorePoolSize());
        // 100
        System.out.println(poolExecutor1.getMaximumPoolSize());
        // 20s
        System.out.println(poolExecutor1.getKeepAliveTime(TimeUnit.SECONDS));
        // SynchronousQueue
        System.out.println(poolExecutor1.getQueue().getClass());
        // cache_pool_-0
        System.out.println(poolExecutor1.getThreadFactory().newThread(() -> {
        }).getName());
        poolExecutor1.shutdown();
    }

    @Test
    public void fixPool() {
        ThreadPoolExecutor build = ThreadPoolBuilder.fixedPool().build();
        // 1
        System.out.println(build.getCorePoolSize());
        // 1
        System.out.println(build.getMaximumPoolSize());
        // 0
        System.out.println(build.getKeepAliveTime(TimeUnit.SECONDS));
        // LinkedBlockingQueue
        System.out.println(build.getQueue().getClass().getName());
        build.shutdown();

        ThreadPoolExecutor build1 = ThreadPoolBuilder.fixedPool()
                .setPoolSize(20).setQueueSize(100).setThreadFactory(ThreadPoolUtil.buildThreadFactory("fix-pool")).setDaemon(true)
                .build();
        // 20
        System.out.println(build1.getCorePoolSize());
        // 20
        System.out.println(build1.getMaximumPoolSize());
        // 0
        System.out.println(build1.getKeepAliveTime(TimeUnit.SECONDS));
        // ArrayBlockingQueue
        System.out.println(build1.getQueue().getClass().getName());
        System.out.println(build1.getThreadFactory().newThread(() -> {
        }).getName());
        build1.shutdown();
    }

    @Test
    public void scheduledPool() {
        ScheduledThreadPoolExecutor build = ThreadPoolBuilder.scheduledPool().build();
        // 1
        System.out.println(build.getCorePoolSize());
        System.out.println(build.getMaximumPoolSize());
        // 0
        System.out.println(build.getKeepAliveTime(TimeUnit.SECONDS));
        // ScheduledThreadPoolExecutor$DelayedWorkQueue
        System.out.println(build.getQueue().getClass().getName());
        build.shutdown();


        ScheduledThreadPoolExecutor build1 = ThreadPoolBuilder.scheduledPool()
                .setPoolSize(100).setThreadFactory(ThreadPoolUtil.buildThreadFactory("schedule-pool"))
                .build();
        // 100
        System.out.println(build1.getCorePoolSize());
        System.out.println(build1.getMaximumPoolSize());
        // 0
        System.out.println(build1.getKeepAliveTime(TimeUnit.SECONDS));
        // ScheduledThreadPoolExecutor$DelayedWorkQueue
        System.out.println(build1.getQueue().getClass().getName());
        System.out.println(build1.getThreadFactory().newThread(() -> {
        }).getName());
        build1.shutdown();
    }

    @Test
    public void quequablePool() {
        QueuableCachedThreadPool build = ThreadPoolBuilder.queuableCachedPool().build();
        System.out.println(build.getCorePoolSize());
        System.out.println(build.getMaximumPoolSize());
        // 0
        System.out.println(build.getKeepAliveTime(TimeUnit.SECONDS));
        // ScheduledThreadPoolExecutor$DelayedWorkQueue
        System.out.println(build.getQueue().getClass().getName());
        build.shutdown();
    }
}
