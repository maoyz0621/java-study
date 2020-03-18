package com.myz.design.proxy;

import com.myz.design.proxy.cglib.CglibClient;
import com.myz.design.proxy.cglib.TargetInterceptor;
import com.myz.design.proxy.cglib.TargetObject;
import com.myz.design.proxy.dynamic1.ProxyClient;
import com.myz.design.proxy.dynamic1.RealSubject;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author maoyz
 */
public class DynamicProxyFactory<T> {

    private final Class<T> target;

    public DynamicProxyFactory(Class<T> target) {
        this.target = target;
    }

    public T newInstance(Object dynamicMethod) {
        Class<?>[] interfaces = target.getInterfaces();

        if (interfaces.length == 0) {
            return new CglibClient<T>(target).newInstance((MethodInterceptor) dynamicMethod);
        } else {
            return new ProxyClient<T>(target).newInstance();
        }
    }

    public static void main(String[] args) {
        // 基于类
        DynamicProxyFactory<TargetObject> proxyFactory0 = new DynamicProxyFactory<>(TargetObject.class);
        proxyFactory0.newInstance(new TargetInterceptor());

        // 基于接口实现的类
        DynamicProxyFactory<RealSubject> proxyFactory1 = new DynamicProxyFactory<>(RealSubject.class);
        proxyFactory1.newInstance(null);
    }
}
