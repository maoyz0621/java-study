/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.lifecycleCallbacks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author maoyz0621 on 2021/3/24
 * @version v1.0
 */
@Configuration
public class LifecycleCallbacks {

    @Bean(initMethod = "init1")
    public LifecycleCallbacksBean lifecycleCallbacksBean() {
        return new LifecycleCallbacksBean();
    }

}