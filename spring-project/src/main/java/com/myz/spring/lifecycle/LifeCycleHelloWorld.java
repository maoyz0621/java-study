package com.myz.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * InitializingBean
 * DisposableBean
 *
 * @author maoyz
 */
public class LifeCycleHelloWorld implements InitializingBean, DisposableBean {

    public LifeCycleHelloWorld() {
        System.out.println("执行实例化...");
    }

    public void init() {
        System.out.println("执行init()");
    }

    public void destory() {
        System.out.println("执行destory()");
    }

    public void defaultInit() {
        System.out.println("执行defaultInit()");
    }

    public void defaultDestory() {
        System.out.println("执行defaultDestory()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行DisposableBean destroy() ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean afterPropertiesSet() ...");
    }

    public void say() {
        System.out.println("执行say()");
    }

}
