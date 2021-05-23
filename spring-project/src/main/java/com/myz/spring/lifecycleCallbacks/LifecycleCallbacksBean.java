/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.LifecycleCallbacks;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author maoyz0621 on 2021/3/24
 * @version v1.0
 */
public class LifecycleCallbacksBean implements InitializingBean {

    // 1
    public LifecycleCallbacksBean() {
        System.out.println("LifecycleCallbacksBean 构造");
    }

    // 2
    @PostConstruct
    public void init() {
        System.out.println("LifecycleCallbacksBean PostConstruct");
    }

    /**
     * 3
     * <p>
     * 属性注入完之后
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("LifecycleCallbacksBean afterPropertiesSet");
    }

    // 4
    public void init1() {
        System.out.println("LifecycleCallbacksBean init1");
    }
}