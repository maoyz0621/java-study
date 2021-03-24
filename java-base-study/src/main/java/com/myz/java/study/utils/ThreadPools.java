/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:46  Inc. All rights reserved.
 */
package com.myz.java.study.utils;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class ThreadPools {

    public static Builder builder() {
        return Builder.buildThreadPoolExecutor();
    }

    public static final class Builder {
        private int corePoolSize;
        private int maximumPoolSize;
        private BlockingQueue<Runnable> workQueue;
        private long keepAliveTime;
        private TimeUnit timeUnit = TimeUnit.SECONDS;
        private ThreadFactory threadFactory = Executors.defaultThreadFactory();
        private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        private Builder() {
        }

        public static Builder buildThreadPoolExecutor() {
            return new Builder();
        }

        public Builder corePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
            return this;
        }

        public Builder maximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        public Builder workQueue(BlockingQueue<Runnable> workQueue) {
            this.workQueue = workQueue;
            return this;
        }

        public Builder useLinkedBlockingQueue() {
            this.workQueue = new LinkedBlockingDeque<>();
            return this;
        }

        public Builder useLinkedBlockingQueue(int capacity) {
            this.workQueue = new LinkedBlockingDeque<>(capacity);
            return this;
        }

        public Builder useArrayBlockingQueue(int capacity) {
            this.workQueue = new ArrayBlockingQueue<>(capacity);
            return this;
        }

        public Builder useArrayBlockingQueue(int capacity, boolean fair) {
            this.workQueue = new ArrayBlockingQueue<>(capacity, fair);
            return this;
        }

        public Builder useSynchronousQueue() {
            this.workQueue = new SynchronousQueue<>();
            return this;
        }

        public Builder useSynchronousQueue(boolean fair) {
            this.workQueue = new SynchronousQueue<>(fair);
            return this;
        }

        public Builder usePriorityBlockingQueue() {
            this.workQueue = new PriorityBlockingQueue<>();
            return this;
        }

        public Builder usePriorityBlockingQueue(int capacity) {
            this.workQueue = new PriorityBlockingQueue<>(capacity);
            return this;
        }

        public Builder usePriorityBlockingQueue(int capacity,
                                                Comparator<? super Runnable> comparator) {
            this.workQueue = new PriorityBlockingQueue<>(capacity, comparator);
            return this;
        }

        public Builder keepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
            return this;
        }

        public Builder threadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

        public Builder rejectPolicy(RejectedExecutionHandler handler) {
            this.handler = handler;
            return this;
        }

        public Builder useAbortPolicy() {
            this.handler = new ThreadPoolExecutor.AbortPolicy();
            return this;
        }

        public Builder useCallerRunsPolicy() {
            this.handler = new ThreadPoolExecutor.CallerRunsPolicy();
            return this;
        }

        public Builder useDiscardOldestPolicy() {
            this.handler = new ThreadPoolExecutor.DiscardOldestPolicy();
            return this;
        }

        public Builder useDiscardPolicy() {
            this.handler = new ThreadPoolExecutor.DiscardPolicy();
            return this;
        }

        public ThreadPoolExecutor build() {
            return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue, threadFactory, handler);
        }
    }
}