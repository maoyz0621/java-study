/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-24 16:40  Inc. All rights reserved.
 */
package com.myz.java.study.loadbalance;

import java.util.List;

/**
 * @author maoyz
 */
public interface LoadBalancer<T> {

    T select(List<T> elements);
}