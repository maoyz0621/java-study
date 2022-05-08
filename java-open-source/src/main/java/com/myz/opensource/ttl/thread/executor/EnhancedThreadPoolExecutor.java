/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl.thread.executor;

import com.myz.opensource.ttl.thread.EnhancedCallable;
import com.myz.opensource.ttl.thread.EnhancedRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author maoyz0621 on 2022/1/21
 * @version v1.0
 */
public class EnhancedThreadPoolExecutor extends ThreadPoolExecutor {

    public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(EnhancedRunnable.get(command));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(EnhancedRunnable.get(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(EnhancedRunnable.get(task), result);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(EnhancedCallable.get(task));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return super.invokeAny(this.getEnhancedTasks(tasks));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return super.invokeAny(this.getEnhancedTasks(tasks), timeout, unit);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return super.invokeAll(this.getEnhancedTasks(tasks));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return super.invokeAll(this.getEnhancedTasks(tasks), timeout, unit);
    }

    private <T> Collection<? extends Callable<T>> getEnhancedTasks(Collection<? extends Callable<T>> tasks) {
        if (tasks == null) {
            return null;
        }
        Collection<EnhancedCallable<T>> newTasks = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            newTasks.add(EnhancedCallable.get(task));
        }
        return newTasks;
    }
}