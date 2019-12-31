package com.myz.design.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 目标对象拦截器
 *
 * @author maoyz on 18-3-9.
 */
public class TargetInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetInterceptor.class);


    /**
     * @param obj         目标对象
     * @param method      目标方法
     * @param args        参数
     * @param methodProxy 方法代理对象
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        LOGGER.info("执行TargetInterceptor, 被代理类proxy = {}, method = {}, 返回类型returnType = {}, 传递参数args = {}", obj.getClass(), method.getName(), method.getReturnType(), args);

        Object result = null;
        // 调用方法
        try {
            // 通过代理子类调用父类的方法, 不要使用invoke(), 不要使用invoke(),不要使用invoke()
            result = methodProxy.invokeSuper(obj, args);
            LOGGER.info("返回结果result = {}", result);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
