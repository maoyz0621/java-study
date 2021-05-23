/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.ioc.circularReference.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作用域 singleton 和 prototype
 *
 * @author maoyz0621 on 19-2-27
 * @version: v1.0
 */
@Component
public class ConstructorIocB {

    private ConstructorIocA setterIocA;

    public ConstructorIocB() {
        System.out.println("IocB ...");
    }

    @Autowired
    public ConstructorIocB(ConstructorIocA setterIocA) {
        this.setterIocA = setterIocA;
    }
}
