/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.proxy.dynamic3;

import java.lang.reflect.Proxy;

/**
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class ProxyClient {

    /**
     * 绑定委托对象，被代理类实例化，返回代理类对象,Class类，接口本身
     */
    public <T> T newInstance(Class<T> clazz) {
        // 返回代理类对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MyInvocationHandler(clazz));
    }

    public static void main(String[] args) {
        Subject sub = new ProxyClient().newInstance(Subject.class);
        // Subject调用
        sub.produce();

        System.out.println("============");

        sub.process();

    }
}
