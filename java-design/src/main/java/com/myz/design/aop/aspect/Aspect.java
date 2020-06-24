/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-23 09:52  Inc. All rights reserved.
 */
package com.myz.design.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author maoyz
 */
public interface Aspect {

    /**
     *
     * @param target 目标对象
     * @param method 目标对象方法
     * @param args 目标对象参数
     * @return
     */
    boolean before(Object target, Method method, Object[] args);

    boolean after(Object target, Method method, Object[] args, Object returnVal);

    boolean afterException(Object target, Method method, Object[] args, Throwable t);
}