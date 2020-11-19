package com.myz.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 目标对象拦截器
 * 基于继承实现代理,所有无法对static和final类进行代理
 *
 * @author maoyz on 18-3-9.
 */
public class TargetInterceptor implements MethodInterceptor {

    /**
     * @param o           目标对象
     * @param method      目标方法
     * @param objects     参数
     * @param methodProxy 方法代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        System.out.println("调用前 ...");

        // 调用方法invokeSuper(对象, 参数)
        result = methodProxy.invokeSuper(o, objects);

        System.out.println("调用后... " + result);
        return result;
    }
}
