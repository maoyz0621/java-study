/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.proxy.dynamic;

/**
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class ProxyClient {

    public static void main(String[] args) {

        Subject proxyFactory = new ProxySubject(new RealSubject());
        proxyFactory.produce();

        System.out.println("============");

        MyInvocationHandler handler = new MyInvocationHandler();
        // 强转为Subject
        Subject sub = (Subject) handler.bind(new RealSubject());
        // Subject调用
        sub.produce();

        System.out.println("============");

        sub.process();

    }
}
