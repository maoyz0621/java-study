/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.ttl.thread;

import com.alibaba.ttl.TtlCallable;

import java.util.concurrent.Callable;

/**
 * @author maoyz0621 on 2022/5/8
 * @version v1.0
 */
public class EnhancedCallable<V> implements Callable<V> {

    private final TtlCallable<V> ttlCallable;

    private EnhancedCallable(Callable<V> callable) {
        this.ttlCallable = callable instanceof TtlCallable ? (TtlCallable<V>) callable : TtlCallable.get(callable);
    }

    private EnhancedCallable(Callable<V> callable, boolean releaseTtlValueReferenceAfterCall) {
        this.ttlCallable = callable instanceof TtlCallable ? (TtlCallable<V>) callable : TtlCallable.get(callable, releaseTtlValueReferenceAfterCall);
    }

    @Override
    public V call() throws Exception {
        return ttlCallable.call();
    }

    public static <T> EnhancedCallable<T> get(Callable<T> callable) {
        if (callable == null) {
            return null;
        }
        return callable instanceof EnhancedCallable ? (EnhancedCallable<T>) callable : new EnhancedCallable<>(callable);
    }

    public static <T> EnhancedCallable<T> get(Callable<T> callable, boolean releaseTtlValueReferenceAfterCall) {
        if (callable == null) {
            return null;
        }
        return callable instanceof EnhancedCallable ? (EnhancedCallable<T>) callable : new EnhancedCallable<>(callable, releaseTtlValueReferenceAfterCall);
    }

}