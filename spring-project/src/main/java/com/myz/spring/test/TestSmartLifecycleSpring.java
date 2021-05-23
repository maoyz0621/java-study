/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.test;

import com.myz.spring.smartLifecycle.MySmartLifecycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621 on 2021/3/24
 * @version v1.0
 */
public class TestSmartLifecycleSpring {

    @Test
    public void testAnnotationXml() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MySmartLifecycle.class);
        context.stop();
    }
}