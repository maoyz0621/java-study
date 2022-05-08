/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl.spring;

import com.myz.opensource.ttl.thread.EnhancedCallable;
import com.myz.opensource.ttl.thread.EnhancedRunnable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/**
 * @author maoyz0621 on 2022/5/8
 * @version v1.0
 */
public class EnhancedThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {
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

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        return super.schedule(EnhancedRunnable.get(task), trigger);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        return super.schedule(EnhancedRunnable.get(task), startTime);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
        return super.scheduleAtFixedRate(EnhancedRunnable.get(task), startTime, period);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        return super.scheduleAtFixedRate(EnhancedRunnable.get(task), period);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        return super.scheduleWithFixedDelay(EnhancedRunnable.get(task), startTime, delay);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return super.scheduleWithFixedDelay(EnhancedRunnable.get(task), delay);
    }
}