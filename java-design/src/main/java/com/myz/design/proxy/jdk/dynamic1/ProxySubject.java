/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.proxy.jdk.dynamic1;

/**
 * 静态代理类
 * 代理对象,也要实现响应接口
 *
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class ProxySubject implements Subject {
      /**
     * 传入接口
     */
    private Subject obj;

    /**
     * 构造注入
     */
    public ProxySubject(Subject obj) {
        this.obj = obj;
    }

    /**
     * setter注入
     */
    public void setObj(Subject obj) {
        this.obj = obj;
    }

    @Override
    public void produce() {
        System.out.println("我是代理类，先执行");
        this.obj.produce();
        System.out.println("执行结束...");
    }

    @Override
    public void process() {
        System.out.println("ProxySubject process...");
    }
}
