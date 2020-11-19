/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * synchronized 属于可重入锁
 * 普通同步方法，同一对象
 * 普通同步方法，不同对象
 * 两个静态同步方法，同一对象
 * 两个静态同步方法，不同对象
 * 一个静态同步方法，一个普通同步方法．同一对象
 * 一个静态同步方法，一个普通同步方法．不同对象
 * <p>
 * this锁和全局锁不同
 *
 * @author maoyz0621 on 19-11-6
 * @version v1.0
 */
public class SynchronizedTest {

    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            100,
            150,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        Test test0 = new Test();

        // reentrantSync(test);

        // thisSync(test);
        //
        // thisSyncDiffObj(test, test0);
        //
        // staticSync(test);
        //
        // staticSyncDiffObj(test, test0);
        //
        // oneStaticOneNormalSync(test);
        //
        oneStaticOneNormalSyncDiffObj(test, test0);

        fixedThreadPool.shutdown();
    }

    private static void oneStaticOneNormalSyncDiffObj(Test test, Test test0) {
        System.out.println("一个静态同步方法，一个普通同步方法．不同对象");
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test.m5();
            }
        });
        fixedThreadPool.execute(test0::m2);
    }

    private static void oneStaticOneNormalSync(Test test) {
        System.out.println("一个静态同步方法，一个普通同步方法．同一对象");
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test.m5();
            }
        });
        fixedThreadPool.execute(test::m2);
    }

    private static void staticSyncDiffObj(Test test, Test test0) {
        System.out.println("两个静态同步方法，不同对象");
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test.m5();
            }
        });
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test0.m4();
            }
        });
    }

    private static void staticSync(Test test) {
        System.out.println("两个静态同步方法，同一对象");
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test.m5();
            }
        });
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                test.m4();
            }
        });
    }

    private static void thisSyncDiffObj(Test test, Test test0) {
        System.out.println("普通同步方法，不同对象");
        fixedThreadPool.execute(test::m3);
        fixedThreadPool.execute(test0::m2);
    }

    /**
     * this　对象锁
     * 普通同步方法，同一对象
     *
     * @param test
     */
    private static void thisSync(Test test) {
        System.out.println("普通同步方法，同一对象");
        fixedThreadPool.execute(test::m3);
        fixedThreadPool.execute(test::m2);
    }

    /**
     * synchronized 属于可重入锁
     *
     * @param test
     */
    private static void reentrantSync(Test test) {
        System.out.println("synchronized 属于可重入锁");
        fixedThreadPool.execute(test::m1);
        fixedThreadPool.execute(test::m1);
    }
}


class Test {

    public void m0() {
        System.out.println(Thread.currentThread().getName() + " m0()");
    }

    /**
     * synchronized修饰的方法, 调用synchronized修饰的m2()
     */
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1() + m2()");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + " m2()");
    }

    /**
     * synchronized对象锁 this
     */
    public synchronized void m3() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m3()　sleep");
    }

    public static synchronized void m4() {
        System.out.println(Thread.currentThread().getName() + " m4() static");
    }

    /**
     * static修饰synchronized　全局锁
     */
    public static synchronized void m5() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m5() static sleep");
    }

}