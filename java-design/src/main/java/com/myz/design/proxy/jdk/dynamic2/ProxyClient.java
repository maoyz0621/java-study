/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.proxy.jdk.dynamic2;

import java.lang.reflect.Proxy;

/**
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class ProxyClient {

    /**
     * 绑定委托对象，绑定接口，实例对象
     */
    public <T> T newInstance(T clazz) {
        // 返回代理类对象
        return (T) Proxy.newProxyInstance(clazz.getClass().getClassLoader(),
                clazz.getClass().getInterfaces(),
                new MyInvocationHandler(clazz));
    }

    public static void main(String[] args) {
        // 强转为Subject
        Subject sub = new ProxyClient().newInstance(new RealSubject());
        // Subject调用
        sub.produce();

        System.out.println("============");

        sub.process();
    }
}
