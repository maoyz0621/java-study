/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.abstractClass;

import org.springframework.stereotype.Component;

/**
 * @author maoyz0621 on 2021/4/16
 * @version v1.0
 */
@Component
public class A2 extends AbstractA {

    @Override
    void play() {
        System.out.println("抽象实现类 a2");
    }
}