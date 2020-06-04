/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.io.nio.selector;

import java.util.LinkedList;
import java.util.List;

/**
 * 线程池
 *
 * @author maoyz on 18-10-22
 * @version: v1.0
 */
public class ThreadPool {

    List<Runnable> threadPools = new LinkedList<Runnable>();

    public ThreadPool(int poolSize) {
        Runnable runnable = new WorkerThread(this);
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(runnable);
            thread.setName("Worker " + (i + 1));
            thread.start();
            threadPools.add(thread);
        }
    }

    /**
     * 获取工作线程
     */
    public synchronized Runnable getWorker() {
        Runnable runnable = null;
        if (threadPools.size() > 0) {
            runnable = threadPools.remove(0);
        }
        return runnable;
    }

    /**
     *
     */
    public synchronized void returnWorker(Runnable runnable) {
        threadPools.add(runnable);
    }
}
