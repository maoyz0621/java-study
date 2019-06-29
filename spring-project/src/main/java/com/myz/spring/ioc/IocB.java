/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 作用域 singleton 和 prototype
 *
 * @author maoyz0621 on 19-2-27
 * @version: v1.0
 */
@Component
@Scope("prototype")
public class IocB {

    /**
     * 出现循环依赖 方法1
     */
    @Autowired
    @Lazy
    private IocA iocA;

    public IocB() {
        System.out.println("IocB ...");
    }

    /**
     * 出现循环依赖 方法2 加上@Lazy
     */
    // @Autowired
    // @Lazy
    public IocB(IocA iocA) {
        this.iocA = iocA;
    }

    public IocA getIocA() {
        return iocA;
    }

    public void setIocA(IocA iocA) {
        this.iocA = iocA;
    }
}
