/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
 * 公平锁:若JVM选择了等待时间最长的一个线程持有锁，则为公平锁（保证每个线程的等待时间均衡）
 * 需要注意的是，即使是公平锁，JVM也无法做到准确的“公平”，在程序中不能以此作为计算
 * 显示锁默认是非公平锁，可以在构造函数中增加true来声明公平锁，而synchronized只能是实现非公平锁
 * <p>
 * 4.Lock是代码级，synchronized是JVM级的
 * Lock是通过编码实现的，synchronized是在运行期由JVM解释的，相对来说synchronized的优化可能性更高，
 * 毕竟是在最核心部分支持的，Lock的优化则需要用户自行考虑。
 * 灵活、强大选择Lock;快捷、安全选择synchronized。
 *
 * @author maoyz0621 on 19-3-25
 * @version v1.0
 */
@Slf4j
public class ReentrantReadWriteLockTest {
    private static final Logger logger = LoggerFactory.getLogger(ReentrantReadWriteLockTest.class);

    private final TreeMap<String, String> treeMap = new TreeMap<>();

    Integer index;

    AtomicInteger atomicSize;

    private volatile boolean useCache;

    /* 读写锁 */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    /* 读锁 */
    private final Lock readLock = lock.readLock();
    /* 写锁 */
    private final Lock writeLock = lock.writeLock();

    /////////////////////////////////////////// TreeMap Start ////////////////////////////////////////////////
    public String get(String key) {
        readLock.lock();
        try {
            logger.debug("{}", key);
            Thread.sleep(100);
            String val = treeMap.get(key);
            logger.debug("{} get end", key);
            return val;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return null;
    }

    public String[] allKeys() {
        readLock.lock();
        try {
            return (String[]) treeMap.keySet().toArray();
        } finally {
            readLock.unlock();
        }
    }

    public String put(String key, String val) {
        writeLock.lock();
        try {
            logger.debug("key = {}, val = {}", key, val);
            Thread.sleep(300);
            treeMap.put(key, val);
            logger.debug("key = {}, val = {} put end", key, val);
            return val;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    public void clear() {
        writeLock.lock();
        try {
            treeMap.clear();
        } finally {
            writeLock.unlock();
        }
    }
    ////////////////////////////////////////// TreeMap End /////////////////////////////////////////////////


    ////////////////////////////////////////// Start /////////////////////////////////////////////////

    /**
     * 读操作，可并发执行
     */
    public void read() {
        readLock.lock();
        try {
            Thread.sleep(1000);
            log.debug("============  {} Start read ===============", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 写操作，同时只允许一个执行
     */
    public void write() {
        writeLock.lock();
        try {
            Thread.sleep(1000);
            log.debug("============ {} Start write ===============", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }
    /////////////////////////////////////////// End ////////////////////////////////////////////////


    ////////////////////////////////////////// Start /////////////////////////////////////////////////
    public void procee() {
        readLock.lock();
        if (!useCache) {
            // 1 先释放readLock
            readLock.unlock();
            // 2 获取writeLock
            writeLock.lock();

            try {
                if (!useCache) {
                    useCache = true;
                }
            } finally {
                writeLock.unlock();
            }
        }

        try {
            add();
        } finally {
            readLock.unlock();
        }
    }

    public void print() {
        System.out.println("index = " + index);
        System.out.println("atomicSize = " + atomicSize);

    }

    private void add() {
        index++;
        atomicSize.getAndAdd(1);
    }
    ////////////////////////////////////////// End /////////////////////////////////////////////////

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
        ReentrantReadWriteLockTest lockTest = new ReentrantReadWriteLockTest();
        for (int i = 0; i < 10; i++) {
            String j = i + "";
            fixedThreadPool.execute(() -> lockTest.put(j, j));
        }

        for (int i = 0; i < 10; i++) {
            String j = i + "";
            fixedThreadPool.execute(() -> lockTest.get(j));
        }

        fixedThreadPool.shutdown();
    }
}