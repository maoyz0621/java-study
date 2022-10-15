/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.test;

import com.myz.spring.func.FuncConfig;
import com.myz.spring.func.ServiceProcessor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621 on 2022/10/15
 * @version v1.0
 */
public class ServiceProcessorTest {

    @Test
    public void testAnnotationConfigAutowired() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FuncConfig.class);
        ServiceProcessor bean = context.getBean(ServiceProcessor.class);
        bean.process("a",null,null);
    }
}