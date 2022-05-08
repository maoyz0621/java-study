/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl.thread;

import com.alibaba.ttl.TtlRunnable;

/**
 * @author maoyz0621 on 2022/1/21
 * @version v1.0
 */
public class EnhancedRunnable implements Runnable {

    private final TtlRunnable ttlRunnable;

    private EnhancedRunnable(Runnable runnable) {
        this.ttlRunnable = runnable instanceof TtlRunnable ? (TtlRunnable) runnable : TtlRunnable.get(runnable);
    }

    private EnhancedRunnable(Runnable runnable, boolean releaseTtlValueReferenceAfterRun) {
        this.ttlRunnable = runnable instanceof TtlRunnable ? (TtlRunnable) runnable : TtlRunnable.get(runnable, releaseTtlValueReferenceAfterRun);
    }

    @Override
    public void run() {
        this.ttlRunnable.run();
    }

    public static EnhancedRunnable get(Runnable runnable) {
        if (runnable == null) {
            return null;
        }
        return runnable instanceof EnhancedRunnable ? (EnhancedRunnable) runnable : new EnhancedRunnable(runnable);
    }

    public static EnhancedRunnable get(Runnable runnable, boolean releaseTtlValueReferenceAfterRun) {
        if (runnable == null) {
            return null;
        }
        return runnable instanceof EnhancedRunnable ? (EnhancedRunnable) runnable : new EnhancedRunnable(runnable, releaseTtlValueReferenceAfterRun);
    }
}