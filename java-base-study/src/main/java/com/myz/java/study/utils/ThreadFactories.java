/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:46  Inc. All rights reserved.
 */
package com.myz.java.study.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author maoyz
 */
public final class ThreadFactories {

    private ThreadFactories() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ThreadFactory namedThreadFactory(final String groupName) {
        return namedThreadFactory(groupName, false);
    }

    public static ThreadFactory namedThreadFactory(final String groupName, final boolean daemon) {
        return namedThreadFactory(groupName, daemon, null);
    }

    public static ThreadFactory namedThreadFactory(final String groupName, final boolean daemon, final Thread.UncaughtExceptionHandler exceptionHandler) {
        if (StringUtils.isEmpty(groupName)) {
            throw new IllegalArgumentException("Group name must not be null or empty!");
        }
        return new ThreadFactory() {

            private final AtomicInteger num = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                try {
                    if (thread.isDaemon() != daemon) {
                        thread.setDaemon(daemon);
                    }
                } catch (Exception ignored) {
                    // Doesn't matter even if failed to set.
                }
                thread.setName(groupName + num.getAndIncrement());
                if (exceptionHandler != null) {
                    thread.setUncaughtExceptionHandler(exceptionHandler);
                }
                return thread;
            }
        };
    }

    public static class Builder {

        public Builder() {
        }

        private String groupName;

        private boolean daemon;

        private Thread.UncaughtExceptionHandler exceptionHandler;

        public Builder groupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder daemon(boolean daemon) {
            this.daemon = daemon;
            return this;
        }

        public Builder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
            this.exceptionHandler = exceptionHandler;
            return this;
        }

        public ThreadFactory build() {
            return ThreadFactories.namedThreadFactory(groupName, daemon, exceptionHandler);
        }
    }

}