package com.myz.spring.test;

import com.myz.spring.aware.MyApplicationContextAware;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Aware测试
 *
 * 先加载.xml中的bean，在执行method
 */
public class TestAwareSpring {

    /**
     * ApplicationContextAware测试
     */
    @Test
    public void testApplicationContextAware() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aware.xml");
        System.out.println(context.getBean("myApplicationContextAware").hashCode());

        System.out.println("---------------------------");

        System.out.println(MyApplicationContextAware.getContext().getBean(MyApplicationContextAware.class).hashCode());

    }

    /**
     * BeanFactoryAware测试
     */
    @Test
    public void testBeanFactoryAware() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aware.xml");
        System.out.println(context.getBean("myBeanFactoryAware").hashCode());
    }

    /**
     * BeanNameAware测试
     */
    @Test
    public void testMyBeanNameAware() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aware.xml");
        System.out.println(context.getBean("myBeanNameAware").hashCode());
    }

    /**
     * ApplicationContextAware和BeanNameAware
     */
    @Test
    public void testAware() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-aware.xml");
        System.out.println(context.getBean("myAware").hashCode());

        System.out.println("---------------------");

        System.out.println(context.getBean("myAware1").hashCode());
    }

}
