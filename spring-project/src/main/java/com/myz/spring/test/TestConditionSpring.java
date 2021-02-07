/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.test;

import com.myz.spring.condition.ConditionBean;
import com.myz.spring.condition.ConditionConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
public class TestConditionSpring {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);

        ConditionBean bean = context.getBean(ConditionBean.class);
    }
}
