package com.myz.java.study.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * Runnable  new Thread(Runnable target, String name)创建线程
 * Thread.currentThread()当前运行线程
 * getName()
 * getPriority()
 * getID()
 * setDaemon(true) 当进程中只剩下守护线程时,所有的守护线程强制终止
 */
class MyThread1 implements Runnable {
    private int count = 100;
//	private static Object object = new Object();	//可以充当锁对象

    @Override
    public void run() {
        /**
         * 此时的object不是共享对象，不能充当锁对象
         */
//		final Object object = new Object();

        while (true) {
            //同步代码块
//			synchronized (object) {
            // this表示当前MyThread1对象
            synchronized (this) {
                notifyAll();
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + count--);
                    /**
                     * 注意此处yield()和sleep()两者方法的区别 sleep()可以按照顺序执行，而yield()则是无序的
                     */
                    // Thread.yield();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 同步方法
     */
    public synchronized void show() {
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + count--);
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadDemo {
    /**
     * 内部类
     */
    private class MyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                test();
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void test() {
        System.out.println("线程名称:" + Thread.currentThread().getName() +
                ";线程优先级:" + Thread.currentThread().getPriority() +
                ";线程ID:" + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        MyThread thread1 = new ThreadDemo().new MyThread();
        MyThread thread2 = new ThreadDemo().new MyThread();
        Thread t1 = new Thread(thread1, "线程-1");
        // Thread(Runnable target, String name)
        Thread t2 = new Thread(thread2, "线程-2");

        t1.setDaemon(true); //设置守护线程
        t2.setDaemon(true); //设置守护线程

        t1.start();
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束");

        System.out.println("--------------------------------------");

        Runnable runnable = new MyThread1();
        for (int i = 0; i < 3; i++) {
            // 此时线程共用一个runnable,可以实现共享数据
            new Thread(runnable, "线程" + i).start();
        }

    }
}
