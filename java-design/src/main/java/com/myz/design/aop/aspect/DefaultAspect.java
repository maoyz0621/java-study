/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-24 10:00  Inc. All rights reserved.
 */
package com.myz.design.aop.aspect;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author maoyz
 */
public class DefaultAspect implements Aspect, Serializable {

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        return true;
    }


    public boolean after(Object target, Method method, Object[] args) {
        return after(target, method, args, null);
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        return true;
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable t) {
        return true;
    }
}