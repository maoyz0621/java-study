/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:43  Inc. All rights reserved.
 */
package com.myz.java.study.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author maoyz
 */
public class RoundRobinLoadBalancer<T> implements LoadBalancer<T> {

    private volatile int cursor = 0;

    private static final AtomicIntegerFieldUpdater CURSOR_UPDATER = AtomicIntegerFieldUpdater.newUpdater(RoundRobinLoadBalancer.class, "cursor");

    @SuppressWarnings("unchecked")
    @Override
    public T select(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            return null;
        }
        final int size = elements.size();
        if (size == 1) {
            return elements.get(0);
        }
        return elements.get((CURSOR_UPDATER.getAndIncrement(this) & 0x7fffffff) % size);
    }

}