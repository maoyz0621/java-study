/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:43  Inc. All rights reserved.
 */
package com.myz.java.study.loadbalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author maoyz
 */
public abstract class WeightRandomLoadBalancer<T> implements LoadBalancer<T> {

    @Override
    public T select(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            return null;
        }
        int size = elements.size();
        if (size == 1) {
            return elements.get(0);
        }

        int totalWeight = 0;
        for (T e : elements) {
            totalWeight += getWeight(e);
        }

        int pos = ThreadLocalRandom.current().nextInt(totalWeight);
        for (int p = 0, i = 0; i < size; i++) {
            T e = elements.get(i);
            if (pos >= p && pos < (p = getWeight(e) + p)) {
                return e;
            }
        }
        return null;
    }

    protected abstract int getWeight(T e);
}
