/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.abstractClass;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author maoyz0621 on 2021/4/16
 * @version v1.0
 */
@Component
public class ServerA {

    @Resource
    private AbstractA a1;

    public void say() {
        a1.say();
    }
}