/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.proxy.dynamic;

/**
 * 被代理类，目标对象
 * @author maoyz on 2018/9/26
 * @version: v1.0
 */
public class RealSubject implements Subject {

    @Override
    public void produce() {
        System.out.println("RealSubject produce...");
    }

    @Override
    public void process() {
        System.out.println("RealSubject process...");
    }
}
