/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-23 10:01  Inc. All rights reserved.
 */
package com.myz.design.aop;

import com.myz.design.aop.aspect.Aspect;
import com.myz.design.aop.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author maoyz
 */
public final class ProxyUtil {

    /**
     *
     * @param target  目标对象
     * @param aspectClass 切面对象类
     * @param <T> 被代理对象类型
     * @return 代理对象
     */
    public static <T> T proxy(T target, Class<? extends Aspect> aspectClass) {
        return ProxyFactory.createProxy(target, aspectClass);
    }

    /**
     *
     * @param target 目标对象
     * @param aspect 切面对象
     * @param <T> 被代理对象类型
     * @return 代理对象
     */
    public static <T> T proxy(T target, Aspect aspect) {
        return ProxyFactory.createProxy(target, aspect);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newProxyInstance(ClassLoader loader, InvocationHandler invocationHandler, Class<?>... interfaces) {
        return (T) Proxy.newProxyInstance(loader, interfaces, invocationHandler);
    }
}