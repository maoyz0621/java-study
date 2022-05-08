/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl.spring;

import com.myz.opensource.ttl.thread.EnhancedCallable;
import com.myz.opensource.ttl.thread.EnhancedRunnable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author maoyz0621 on 2022/5/8
 * @version v1.0
 */
public class EnhancedThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        super.execute(EnhancedRunnable.get(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(EnhancedRunnable.get(task), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(EnhancedRunnable.get(task));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(EnhancedCallable.get(task));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(EnhancedRunnable.get(task));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(EnhancedCallable.get(task));
    }
}