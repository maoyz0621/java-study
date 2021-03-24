/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:42  Inc. All rights reserved.
 */
package com.myz.java.study.loadbalance;

import java.util.List;

/**
 * @author maoyz
 */
public abstract class BackupLoadBalancer<T> implements LoadBalancer<T> {

    @Override
    public T select(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            return null;
        }
        for (T element : elements) {
            if (isMaster(element)) {
                return element;
            }
        }
        return null;
    }

    protected abstract boolean isMaster(T element);
}