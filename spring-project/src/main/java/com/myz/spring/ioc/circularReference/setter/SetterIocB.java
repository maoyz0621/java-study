/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.ioc.circularReference.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 作用域 singleton 和 prototype
 *
 * @author maoyz0621 on 19-2-27
 * @version: v1.0
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeIocB {

    /**
     * 出现循环依赖 方法1
     */
    @Autowired
    private PrototypeIocA prototypeIocA;

    public PrototypeIocB() {
        System.out.println("IocB ...");
    }

}
