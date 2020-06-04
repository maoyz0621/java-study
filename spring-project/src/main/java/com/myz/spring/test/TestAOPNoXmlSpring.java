package com.myz.spring.test;

import com.myz.spring.aop.annotation.Calculator;
import com.myz.spring.aop.annotation.ICalculator;
import com.myz.spring.aop.aspect_no_xml.AspectConfig;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestAOPNoXmlSpring {

    @Test
    public void testAOP() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AspectConfig.class);
        ICalculator bean = context.getBean(ICalculator.class);
        int add = bean.add(1, 2);
        System.out.println(add);
    }

    @Test
    public void testAOP1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AspectConfig.class);
        Calculator bean = context.getBean(Calculator.class);
        int add = bean.add(1, 2);
        System.out.println(add);
    }


}
