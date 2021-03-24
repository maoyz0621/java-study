/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:44  Inc. All rights reserved.
 */
package com.myz.java.study.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author maoyz
 */
public abstract class WeightRoundRobinLoadBalancer<T> implements LoadBalancer<T> {

    private volatile int cursor = 0;

    private static final AtomicIntegerFieldUpdater CURSOR_UPDATER = AtomicIntegerFieldUpdater.newUpdater(WeightRoundRobinLoadBalancer.class, "cursor");

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

        int totalWeight = 0;
        for (T e : elements) {
            totalWeight += getWeight(e);
        }

        List<T> elementsList = new ArrayList<>(totalWeight);
        for (T e : elements) {
            int weight = getWeight(e);
            for (int i = 0; i < weight; i++) {
                elementsList.add(e);
            }
        }

        return elementsList.get((CURSOR_UPDATER.getAndIncrement(this) & 0x7fffffff) % totalWeight);
    }

    protected abstract int getWeight(T e);

}