/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.test;

import com.myz.spring.lifecycleCallbacks.LifecycleCallbacks;
import com.myz.spring.lifecycleCallbacks.LifecycleCallbacksBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621 on 2021/3/24
 * @version v1.0
 */
public class TestLifecycleCallbacksSpring {

    @Test
    public void testAnnotationXml() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleCallbacks.class);
        LifecycleCallbacksBean p = (LifecycleCallbacksBean) context.getBean(LifecycleCallbacksBean.class);
    }
}