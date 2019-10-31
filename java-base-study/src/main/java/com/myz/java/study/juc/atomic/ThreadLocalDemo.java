package com.myz.java.study.juc.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal 为每个线程创建本地局部变量
 * set()/get()
 *
 * @author maoyz on 18-1-7.
 */
public class ThreadLocalDemo implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalDemo.class);

    private CountDemo countDemo;

    public ThreadLocalDemo(CountDemo countDemo) {
        this.countDemo = countDemo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            logger.debug(Thread.currentThread().getName() + " : "
                    + countDemo.getNumber());
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo(new CountDemo());
        // 三个线程启动
        for (int i = 0; i < 3; i++) {
            new Thread(demo).start();
        }
    }

}

/**
 * 计数器
 */
class CountDemo {

    private AtomicInteger count = new AtomicInteger(0);
    // private static int count = 0;

    // 设置ThreadLocal的初始值

    /**
     * ThreadLocal声明为static
     */
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> {
        // 相当于count++,由于threadLocal++这种操作是个复合操作而非原子操作，会
        // 有线程安全问题(可能在初始化时就获取到相同的ID，所以使用原子变量
        return count.get();
    });

    protected int getNumber() {
        // 自增1,设值
        threadLocal.set(threadLocal.get() + 1);
        //　取值
        return threadLocal.get();
    }
}