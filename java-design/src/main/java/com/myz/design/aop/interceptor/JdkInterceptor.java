/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.aop.interceptor;

import cn.hutool.core.util.ReflectUtil;
import com.myz.design.aop.aspect.Aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Jdk 实现动态代理 InvocationHandler
 *
 * @author maoyz on 2018/9/26
 * @version v1.0
 */
public class JdkInterceptor implements InvocationHandler {

    private final Aspect aspect;
    private final Object target;

    /**
     *
     * @param target 被代理对象
     * @param aspect 切面实现
     */
    public JdkInterceptor(Object target, Aspect aspect) {
        this.aspect = aspect;
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object target = this.target;
        Object result = null;
        if (aspect.before(target, method, args)) {

            ReflectUtil.setAccessible(method);
            try {
                // method是否static
                result = method.invoke(Modifier.isStatic(method.getModifiers()) ? null : target, args);
            } catch (InvocationTargetException e) {

                if (aspect.afterException(target, method, args, e.getTargetException())) {
                    throw e;
                }
            }
        }


        if (aspect.after(target, method, args, result)) {
            return result;
        }

        return null;
    }
}
