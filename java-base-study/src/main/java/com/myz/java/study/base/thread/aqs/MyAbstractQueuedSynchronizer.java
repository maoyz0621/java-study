package com.myz.java.study.base.thread.aqs;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * AbstractQueuedSynchronizer 抽象队列同步器
 *
 * @author maoyz
 */
public class MyAbstractQueuedSynchronizer implements Lock, Serializable {

    private static final long serialVersionUID = -7287118771762774242L;

    private final Sync sync;

    /**
     * 默认非公平锁
     */
    public MyAbstractQueuedSynchronizer() {
        sync = new NonfairSync();
    }

    /**
     * 由开发者决定选用哪种锁
     *
     * @param fair 是否公平锁
     */
    public MyAbstractQueuedSynchronizer(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 由子类实现lock()方法
         */
        abstract void lock();

        /**
         * private volatile int state;
         * <p>
         * protected final int getState()  获取synchronization state的值
         * <p>
         * protected final void setState(int newState)  设置state的值
         * <p>
         * protected final boolean compareAndSetState(int expect, int update)  使用CAS方式更新state的值  compareAndSwapIntXxx
         */
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            // 原子操作
            if (compareAndSetState(0, 1)) {
                // 设置互斥锁
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (!isHeldExclusively()) {
                throw new IllegalArgumentException();
            }
            // 释放当前排它锁
            setExclusiveOwnerThread(null);
            //
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            // 获取当前的排它锁 是否本身
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    /**
     * 公平锁
     */
    static final class FairSync extends Sync {

        private static final long serialVersionUID = 5990115362206877793L;

        @Override
        final void lock() {
            // acquire() 由AbstractQueuedSynchronizer实现
            acquire(1);
        }
    }

    /**
     * 非公平锁
     */
    static final class NonfairSync extends Sync {

        private static final long serialVersionUID = -8676371638527680814L;

        @Override
        final void lock() {
            // 先设置排它锁
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                // acquire() 由AbstractQueuedSynchronizer实现
                acquire(1);
            }
        }
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }

}