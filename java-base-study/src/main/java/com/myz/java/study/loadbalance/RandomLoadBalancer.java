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
public class RandomLoadBalancer<T> implements LoadBalancer<T> {

    @Override
    public T select(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            return null;
        }
        int size = elements.size();
        if (size == 1) {
            return elements.get(0);
        }
        int pos = ThreadLocalRandom.current().nextInt(size);
        return elements.get(pos);
    }
}