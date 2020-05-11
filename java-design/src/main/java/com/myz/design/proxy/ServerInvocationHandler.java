package com.myz.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 参考dubbo 动态代理类InvokerInvocationHandler
 *
 * @author maoyz
 */
public class ServerInvocationHandler<T> implements InvocationHandler {

    private final Class<T> invoker;

    public ServerInvocationHandler(Class<T> handler) {
        this.invoker = handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 返回Object类
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(invoker, args);
        }

        // 方法名称
        String methodName = method.getName();
        // 方法参数
        Parameter[] parameters = method.getParameters();
        if (0 == parameters.length) {
            if ("toString".equals(methodName)) {
                return invoker.toString();
            } else if ("hashCode".equals(methodName)) {
                return invoker.hashCode();
            }
        } else if (1 == parameters.length && "equals".equals(methodName)) {
            return invoker.equals(parameters[0]);
        }

        // 方法调用
        return method.invoke(invoker, args);
    }
}
