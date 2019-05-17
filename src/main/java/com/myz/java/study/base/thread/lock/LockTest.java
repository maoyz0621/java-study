/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.thread.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.Lock支持更细粒度的锁控制;
 * 2.Lock是无阻塞锁，synchronized是阻塞锁;
 * 例:当线程A持有锁的时，线程B也期望获得锁，此时，如果线程是使用的显示锁，则B线程为等待状态（即阻塞）;
 * 如果使用的是内部锁则为阻塞状态。
 * <p>
 * 3.Lock可以实现公平锁，而synchronized只能是非公平锁;
 * 非公平锁:当一个线程A持有锁，而线程B、C处于阻塞状态时，   若线程A释放锁，JVM将从线程B、C随机选择一个线程持有锁并使其获得执行权，
 * 这叫做非公平锁(因为它抛弃了先来后到的顺序);
 * 公平锁:若JVM选择了等待时间最长的一个线程持有锁，则为公平锁（保证每个线程的等待时间均衡）。
 * 需要注意的是，即使是公平锁，JVM也无法做到准确的“公平”，在程序中不能以此作为计算。
 * 显示锁默认是非公平锁，可以在构造函数中增加true来声明公平锁，而synchronized只能是实现非公平锁。
 * <p>
 * 4.Lock是代码级，synchronized是JVM级的
 * Lock是通过编码实现的，synchronized是在运行期由JVM解释的，相对来说synchronized的优化可能性更高，
 * 毕竟是在最核心部分支持的，Lock的优化则需要用户自行考虑。
 * 灵活、强大选择Lock;快捷、安全选择synchronized。
 *
 * @author maoyz0621 on 19-3-25
 * @version: v1.0
 */
public class LockTest {

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

        Task task = new Task();
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> task.read());
        }
        System.out.println("========== read end =========");

        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> task.write());
        }
        System.out.println("========== write end =========");

        System.out.println("************** 运行结束 ****************");
        fixedThreadPool.shutdown();

    }
}

class Task {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    /**
     * 读写锁
     */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    /**
     * 读操作，可并发执行
     */
    public void read() {
        try {
            readLock.lock();
            Thread.sleep(1000);
            logger.debug("============  Start read ===============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 写操作，同时只允许一个执行
     */
    public void write() {
        try {
            writeLock.lock();
            Thread.sleep(1000);
            logger.debug("============  Start write ===============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }
}
