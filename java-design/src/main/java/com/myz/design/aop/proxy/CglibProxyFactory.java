/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-23 10:43  Inc. All rights reserved.
 */
package com.myz.design.aop.proxy;

import com.myz.design.aop.aspect.Aspect;
import com.myz.design.aop.interceptor.CglibInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * 使用 cglib的 net.sf.cglib.proxy.Enhancer
 *
 * @author maoyz
 */
public class CglibProxyFactory extends ProxyFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T proxy(T target, Aspect aspect) {
        // Enhancer是CGLIB的字节码增强器，可以很方便的对类进行拓展
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置拦截类
        enhancer.setCallback(new CglibInterceptor(target, aspect));
        return (T) enhancer.create();
    }
}