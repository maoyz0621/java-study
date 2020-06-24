/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-23 10:04  Inc. All rights reserved.
 */
package com.myz.design.aop.proxy;

import cn.hutool.core.util.ReflectUtil;
import com.myz.design.aop.aspect.Aspect;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 * @author maoyz
 */
public abstract class ProxyFactory {

    public static <T> T createProxy(T target, Class<? extends Aspect> aspectClass) {
        return createProxy(target, ReflectUtil.newInstance(aspectClass));
    }

    public static <T> T createProxy(T target, Aspect aspect) {
        return create().proxy(target, aspect);
    }


    /**
     * SPI机制获取代理对象
     * @return this
     */
    public static ProxyFactory create() {
        ServiceLoader<ProxyFactory> load = ServiceLoader.load(ProxyFactory.class);
        final Iterator<ProxyFactory> iterator = load.iterator();
        while (iterator.hasNext()) {
            try {
                // 取第一个
                return iterator.next();
            } catch (ServiceConfigurationError e) {
                //
            }
        }
        // 默认使用cglib
        return new CglibProxyFactory();
    }

    public abstract <T> T proxy(T target, Aspect aspect);
}