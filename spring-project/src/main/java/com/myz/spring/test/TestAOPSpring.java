package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.aop.annotation.ICalculator;

public class TestAOPSpring {

    /**
     *
     */
    @Test
    public void testAOP() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop.xml");
        ICalculator iCalculator = context.getBean(ICalculator.class);
        int result = iCalculator.add(2, 5);
        System.out.println(result);

        System.out.println("----------无返回值---------");

        iCalculator.noArgs();

        System.out.println("----------抛出异常---------");

        float result1 = iCalculator.divide(4, 0);
        System.out.println(result1);
    }

}
