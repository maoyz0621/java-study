/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl.thread.executor;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author maoyz0621 on 2022/5/8
 * @version v1.0
 */
public class EnhancedExecutors {

    private EnhancedExecutors() {
    }

    public static Executor getEnhancedExecutor(Executor executor) {
        return TtlExecutors.getTtlExecutor(executor);
    }

    public static ExecutorService getEnhancedExecutorService(ExecutorService executorService) {
        return TtlExecutors.getTtlExecutorService(executorService);
    }

    public static ScheduledExecutorService getTtlScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        return TtlExecutors.getTtlScheduledExecutorService(scheduledExecutorService);
    }
}