package com.myz.design.aop.interceptor;

import com.myz.design.aop.aspect.Aspect;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Spring-Cglib 实现动态代理 org.springframework.cglib.proxy.MethodInterceptor
 *
 * @author maoyz on 18-3-9.
 */
public class SpringCglibInterceptor implements MethodInterceptor {

    private final Aspect aspect;
    private final Object target;

    /**
     *
     * @param target 被代理对象
     * @param aspect 切面实现
     */
    public SpringCglibInterceptor(Object target, Aspect aspect) {
        this.aspect = aspect;
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        final Object target = this.target;

        Object result = null;
        if (aspect.before(obj, method, args)) {
            try {
                result = method.invoke(target, args);
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
