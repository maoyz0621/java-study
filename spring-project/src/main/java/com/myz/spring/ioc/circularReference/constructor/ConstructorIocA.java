/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.ioc.circularReference.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作用域 prototype
 *
 * @author maoyz0621 on 19-2-27
 * @version: v1.0
 */
@Component
public class ConstructorIocA {

    private ConstructorIocB constructorIocB;

    public ConstructorIocA() {
        System.out.println("IocA .....");
    }

    @Autowired
    public ConstructorIocA(ConstructorIocB constructorIocB) {
        this.constructorIocB = constructorIocB;
    }
}
