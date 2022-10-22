/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-26 17:24  Inc. All rights reserved.
 */
package com.myz.spring.test;

import com.myz.spring.aop.annotation.CalculatorImp;
import com.myz.spring.aop.annotation.ICalculator;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author maoyz
 */
public class ProxyFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(ProxyFactoryTest.class);

    /**
     * 没有指定target 对象
     * org.springframework.aop.framework.AopConfigException: No advisors and no TargetSource specified
     */
    @Test
    public void createJdkDynamicProxyError() {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定接口
        proxyFactory.addInterface(ICalculator.class);

        ICalculator proxy = (ICalculator) proxyFactory.getProxy();

        log.info("proxy class:{}", proxy.getClass().getName());
        proxy.add(1, 2);
        log.info("proxy:{}", proxy);
    }

    @Test
    public void createJdkDynamicProxy() {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定target对象
        ICalculator calculatorImp = new CalculatorImp();
        proxyFactory.setTarget(calculatorImp);

        proxyFactory.addInterface(ICalculator.class);

        ICalculator proxy = (ICalculator) proxyFactory.getProxy();

        log.info("proxy class:{}", proxy.getClass().getName());
        proxy.add(1, 2);
        log.info("proxy:{}", proxy);
    }

    @Test
    public void createCglibDynamicProxyTarget() {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定target对象
        ICalculator calculatorImp = new CalculatorImp();
        proxyFactory.setTarget(calculatorImp);
        ICalculator proxy = (ICalculator) proxyFactory.getProxy();

        // CalculatorImp$$EnhancerBySpringCGLIB$$fa5e4445
        log.info("proxy class:{}", proxy.getClass().getName());
        proxy.add(1, 2);
        log.info("proxy:{}", proxy);
    }

    @Test
    public void createCglibDynamicProxyTargetProxyTargetClass() {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定target对象
        ICalculator calculatorImp = new CalculatorImp();
        proxyFactory.setTarget(calculatorImp);
        proxyFactory.addInterface(ICalculator.class);

        // CalculatorImp$$EnhancerBySpringCGLIB$$a9139ef5
        proxyFactory.setProxyTargetClass(true);

        ICalculator proxy = (ICalculator) proxyFactory.getProxy();
        log.info("proxy class:{}", proxy.getClass().getName());
        proxy.add(1, 2);
        log.info("proxy:{}", proxy);
    }

    @Test
    public void createJdkDynamicProxyWithAdvisor() {
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定target对象
        CalculatorImp calculatorImp = new CalculatorImp();
        proxyFactory.setTarget(calculatorImp);

        proxyFactory.addInterface(ICalculator.class);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice((MethodInterceptor) invocation -> {
            Object result = invocation.proceed();
            log.info("****************");
            return result;
        });

        proxyFactory.addAdvisor(0, advisor);

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor();
        advisor1.setAdvice(new PerformanceMonitorInterceptor());
        proxyFactory.addAdvisor(1, advisor1);

        ICalculator proxy = (ICalculator) proxyFactory.getProxy();

        log.info("proxy class:{}", proxy.getClass().getName());
        proxy.add(1, 2);
        log.info("proxy:{}", proxy);
    }
}