package com.myz.java.study.base.thread.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AbstractQueuedSynchronizer 抽象队列同步器
 *
 * @author maoyz
 */
public class AbstractQueuedSynchronizerTest extends AbstractQueuedSynchronizer {
    /**
     * private volatile int state;
     * <p>
     * protected final int getState()  获取state的值
     * <p>
     * protected final void setState(int newState)  设置state的值
     * <p>
     * protected final boolean compareAndSetState(int expect, int update)  使用CAS方式更新state的值
     */

    @Override
    protected boolean tryAcquire(int arg) {
        return compareAndSetState(0,1);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }
}
