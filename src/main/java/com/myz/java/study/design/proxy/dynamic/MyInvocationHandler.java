/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类InvocationHandler
 * 只能基于接口代理
 * InvocationHandler
 *
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 此对象为实现接口的被代理类
     */
    private Object obj;

    /**
     * 绑定委托对象，被代理类实例化，返回代理类对象
     */
    public Object bind(Object obj) {
        // 被代理类实例化
        this.obj = obj;
        // 返回代理类对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if ("process".equals(method.getName())) {
            System.out.println("可以先做些事");
            // 执行obj反射方法
            result = method.invoke(this.obj, args);
            System.out.println("还可以先做些事");
        } else {
            result = method.invoke(this.obj, args);
        }
        return result;
    }
}
