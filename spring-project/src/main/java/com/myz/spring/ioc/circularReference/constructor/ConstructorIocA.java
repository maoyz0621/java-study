/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.ioc.circularReference.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作用域 prototype
 *
 * @author maoyz0621 on 19-2-27
 * @version: v1.0
 */
@Component
public class SetterIocA {

    /**
     * 出现循环依赖  方法1
     */
    @Autowired
    private SetterIocB setterIocB;

    public SetterIocA() {
        System.out.println("IocA .....");
    }

}
