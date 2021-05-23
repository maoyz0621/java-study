/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.abstractClass;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 抽象父类
 *
 * @author maoyz0621 on 2021/4/16
 * @version v1.0
 */
@Component("abstractA")
public abstract class AbstractA {

    @Resource
    private BeanServer beanServer;

    public void say() {
        System.out.println("abstract say " + beanServer.play());
        this.play();
    }

    abstract void play();
}