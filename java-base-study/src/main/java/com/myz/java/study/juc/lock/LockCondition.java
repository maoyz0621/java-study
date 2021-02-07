/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现线程等待和唤醒
 * 参考 ArrayBlockingQueue 实现原理
 *
 * @author maoyz0621 on 19-3-25
 * @version: v1.0
 */
public class LockCondition<E> {

    private static final Logger logger = LoggerFactory.getLogger(LockCondition.class);

    public static final int DEFAULT_LIST_SIZE = 3;

    public static final int DEFAULT_EMPTY_LIST = 0;

    /**
     * 默认非公平锁
     */
    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private static int num = 0;

    private List<E> lists = new LinkedList<>();

    int putptr/* 写索引 */, takeptr/* 读索引 */, count/* 队列中存在的数据个数 */;

    /**
     * 放置
     */
    public void put(E obj) throws InterruptedException {
        lock.lock();
        try {
            // 当集合已满,则"添加"线程等待
            while (num == DEFAULT_LIST_SIZE) {
                // 阻塞写线程
                notFull.await();
            }

            lists.add(putptr, obj);
            logger.debug("put param = {}", obj);

            if (++putptr == DEFAULT_LIST_SIZE) {
                putptr = 0;
            }

            num++;
            // 唤醒一个等待的线程
            this.notEmpty.signal();
        } finally {
            // 最后释放锁
            lock.unlock();
        }
    }

    /**
     * 拿取
     */
    public E take() throws InterruptedException {
        lock.lock();
        try {
            // 当集合为空时,"减少"线程等待
            while (num == DEFAULT_EMPTY_LIST) {
                notEmpty.await();
            }

            E str = lists.get(takeptr);
            logger.debug("=========== {}", str);

            if (++takeptr == DEFAULT_LIST_SIZE) {
                putptr = 0;
            }
            --num;
            // 唤醒一个等待的线程
            notFull.signal();
            return str;
        } finally {
            // 最后释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 创建ThreadFactory
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build();
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
                10,
                50,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        LockCondition<String> lockCondition = new LockCondition<>();

        try {
            for (int i = 0; i < 10; i++) {
                int j = i;
                fixedThreadPool.execute(() -> {
                    try {
                        lockCondition.put("aaa:" + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            for (int i = 0; i < 10; i++) {
                fixedThreadPool.execute(() -> {
                    try {
                        lockCondition.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            fixedThreadPool.shutdown();
        }
    }
}
