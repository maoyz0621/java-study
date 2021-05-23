package com.myz.spring.test;

import com.myz.spring.abstractClass.A1;
import com.myz.spring.abstractClass.A2;
import com.myz.spring.abstractClass.AbstractA;
import com.myz.spring.abstractClass.ServerA;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Aware测试
 * <p>
 * 先加载.xml中的bean，在执行method
 */
public class TestAbstractSpring {

    @Test
    public void testAbstractServer() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.myz.spring.abstractClass");

        ServerA bean = context.getBean(ServerA.class);
        bean.say();
    }

    @Test
    public void testAbstract() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.myz.spring.abstractClass");

        AbstractA bean1 = (AbstractA) context.getBean("a1");
        A1 bean10 = (A1) context.getBean("a1");

        System.out.println(bean1);
        System.out.println(bean10);

        System.out.println("===========================");

        AbstractA bean2 = (AbstractA) context.getBean("a2");
        A2 bean20 = (A2) context.getBean("a2");
        System.out.println(bean2);
        System.out.println(bean20);
    }

    @Test
    public void testAbstract1() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.myz.spring.abstractClass");
        AbstractA bean1 = (AbstractA) context.getBean("a1");
        A1 bean10 = (A1) context.getBean("a1");
        bean1.say();
        bean10.say();

        System.out.println("===========================");

        AbstractA bean2 = (AbstractA) context.getBean("a2");
        A2 bean20 = (A2) context.getBean("a2");
        bean2.say();
        bean20.say();
    }

}
