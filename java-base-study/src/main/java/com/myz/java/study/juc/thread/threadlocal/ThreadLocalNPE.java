package com.myz.java.study.juc.thread.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 参考RequestContextHolder
 *
 * @author maoyz
 */
public class ThreadLocalNPE {

    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    ThreadLocal<Long> longThreadLocal = new InheritableThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public Long get() {
        return longThreadLocal.get();
    }

    // 如果get方法返回值为基本类型，则会报空指针异常，如果是包装类型就不会出错，包装类型-拆箱
    public long get0() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();

        // 如果get方法返回值为基本类型，则会报空指针异常，如果是包装类型就不会出错
        System.out.println(threadLocalNPE.get());
        // System.out.println(threadLocalNPE.get0());

        fixedThreadPool.execute(() -> {
            threadLocalNPE.set();
            System.out.println(threadLocalNPE.get());
        });
    }

}
