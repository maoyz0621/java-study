package com.myz.spring.config;

/**
 * @author maoyz on 18-3-6.
 */
public class MyBeanImp implements MyBean {

    /**
     * init()方法
     */
    @Override
    public void init() {
        System.out.println("init ...");
    }

    /**
     * destory()方法
     */
    @Override
    public void destory() {
        System.out.println("destory ...");
    }
}
