package com.myz.design.aop.interceptor;

import com.myz.design.aop.aspect.Aspect;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Cglib 实现动态代理 net.sf.cglib.proxy.MethodInterceptor
 *
 * @author maoyz on 18-3-9.
 */
public class CglibInterceptor implements MethodInterceptor {

    private final Aspect aspect;
    private final Object target;

    /**
     *
     * @param target 被代理对象
     * @param aspect 切面实现
     */
    public CglibInterceptor(Object target, Aspect aspect) {
        this.aspect = aspect;
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * @param obj         目标对象
     * @param method      目标方法
     * @param args        参数
     * @param methodProxy 方法代理对象
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        final Object target = this.target;
        Object result = null;
        if (aspect.before(obj, method, args)) {
            try {
                // 方法一：
                // result = methodProxy.invokeSuper(obj, args);

                // 方法二：
                result = methodProxy.invoke(target, args);
            } catch (InvocationTargetException e) {
                // 只捕获业务异常，放行反射异常
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
