/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-23 10:43  Inc. All rights reserved.
 */
package com.myz.design.aop.proxy;

import com.myz.design.aop.ProxyUtil;
import com.myz.design.aop.aspect.Aspect;
import com.myz.design.aop.interceptor.JdkInterceptor;

/**
 * @author maoyz
 */
public class JdkProxyFactory extends ProxyFactory {

    @Override
    public <T> T proxy(T target, Aspect aspect) {
        return ProxyUtil.newProxyInstance(target.getClass().getClassLoader(),
                new JdkInterceptor(target, aspect),
                target.getClass().getInterfaces());
    }
}