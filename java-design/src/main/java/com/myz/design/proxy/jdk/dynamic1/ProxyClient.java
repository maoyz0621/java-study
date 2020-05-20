/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.proxy.jdk.dynamic1;

import java.lang.reflect.Proxy;

/**
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class ProxyClient<T> {

    private final Class<T> interfaceClazz;

    public ProxyClient(Class<T> interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
    }

    public T newInstance() {
        final JdkInvocationHandler<T> jdkProxy = new JdkInvocationHandler<>(interfaceClazz);
        return newInstance(jdkProxy);
    }

    protected T newInstance(JdkInvocationHandler<T> jdkProxy) {
        // interfaceClazz 分为 接口和Class
        if (interfaceClazz.isInterface()) {
            // T 为接口
            return (T) Proxy.newProxyInstance(
                    interfaceClazz.getClassLoader(),
                    new Class[]{interfaceClazz},
                    jdkProxy);
        } else {
            // T 为实现类
            return (T) Proxy.newProxyInstance(
                    interfaceClazz.getClassLoader(),
                    interfaceClazz.getInterfaces(),
                    jdkProxy);
        }
    }

    /**
     * 绑定委托对象，被代理类实例化，返回代理类对象,Class类,接口的实现类
     */
    // public <T> T newInstance(Class<T> clazz) {
    //     // 返回代理类对象
    //     return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new JdkInvocationHandler(clazz));
    // }
    public static void main(String[] args) {

        Subject proxyFactory = new ProxySubject(new RealSubject());
        proxyFactory.produce();

        System.out.println("===========================================\r\n");

        // 强转为Subject
        Subject sub = (Subject) new ProxyClient(RealSubject.class).newInstance();
        // Subject调用
        sub.produce();

        System.out.println("=============================================\r\n");

        sub.process();

    }
}
