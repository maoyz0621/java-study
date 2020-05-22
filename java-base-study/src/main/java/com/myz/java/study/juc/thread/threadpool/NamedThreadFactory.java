/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-21 17:09  Inc. All rights reserved.
 */
package com.myz.java.study.juc.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author maoyz
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private final AtomicInteger ThreadNum = new AtomicInteger(1);

    private final boolean daemon;

    private final String prefix;

    private final ThreadGroup group;

    public NamedThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }


    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        this.daemon = daemon;
        this.prefix = prefix + "-thread-";

        SecurityManager manager = System.getSecurityManager();
        this.group = (manager == null) ? Thread.currentThread().getThreadGroup() : manager.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, prefix + ThreadNum.getAndIncrement(), 0);
        thread.setDaemon(daemon);
        return thread;
    }
}