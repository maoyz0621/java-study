/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IocA {

    /**
     * 出现循环依赖  方法1
     */
    @Autowired
    @Lazy
    private IocB iocB;

    public IocA() {
        System.out.println("IocA .....");
    }

    /**
     * 出现循环依赖 方法2 加上@Lazy
     */
    // @Autowired
    // @Lazy
    public IocA(IocB iocB) {
        this.iocB = iocB;
    }

    public IocB getIocB() {
        return iocB;
    }

    public void setIocB(IocB iocB) {
        this.iocB = iocB;
    }
}
