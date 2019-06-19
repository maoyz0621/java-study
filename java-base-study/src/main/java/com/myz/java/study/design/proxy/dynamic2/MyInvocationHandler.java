/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.proxy.dynamic2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类InvocationHandler
 * 只能基于接口代理
 * InvocationHandler
 *
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class MyInvocationHandler<T> implements InvocationHandler {

    /**
     * 此对象为实现接口的被代理类
     */
    private T target;

    public MyInvocationHandler(T target) {
        this.target = target;
    }

    public T getTarget() {
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        if ("process".equals(method.getName())) {
            System.out.println("可以先做些事");
            // 执行obj反射方法
            result = method.invoke(target, args);
            System.out.println("还可以先做些事");
        } else {
            result = method.invoke(target, args);
        }
        return result;
    }
}
