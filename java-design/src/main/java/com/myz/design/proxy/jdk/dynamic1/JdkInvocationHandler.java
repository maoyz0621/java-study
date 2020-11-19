/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.proxy.jdk.dynamic1;

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
public class JdkInvocationHandler<T> implements InvocationHandler {

    /**
     * 此对象为实现接口的被代理类
     */
    private Class<T> target;

    public JdkInvocationHandler(Class<T> target) {
        this.target = target;
    }

    public Class<T> getTarget() {
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        // 鉴于methodName
        if ("process".equals(method.getName())) {
            System.out.println("可以先做些事");
            // 执行obj反射方法
            result = method.invoke(target.newInstance(), args);
            System.out.println("还可以先做些事");
        } else {
            result = method.invoke(target.newInstance(), args);
        }
        return result;
    }
}
