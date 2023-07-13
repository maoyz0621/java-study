/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.juc.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Semaphore.release() 只执行一次
 * 完美解决多次release()问题
 *
 * @author maoyz0621 on 2023/7/13
 * @version v1.0
 */
public class SemaphoreReleaseOnlyOnce {

    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final Semaphore semaphore;

    public SemaphoreReleaseOnlyOnce(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void release() {
        if (this.semaphore != null) {
            // CAS
            if (this.atomicBoolean.compareAndSet(false, true)) {
                semaphore.release();
            }
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce = new SemaphoreReleaseOnlyOnce(semaphore);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("" + semaphore.tryAcquire());
            }).start();
        }
        // 场景一
        semaphoreReleaseOnlyOnce.release();
        semaphoreReleaseOnlyOnce.release();
        // 剩余许可数=1


        // 场景二
        // semaphore.release();
        // semaphore.release();
        // 剩余许可数=2

        System.out.println("剩余许可数=" + semaphore.availablePermits());
    }
}