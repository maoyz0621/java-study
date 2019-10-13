package com.myz.spring.test;

import com.myz.spring.factory.DogFactoryBean;
import com.myz.spring.factory.FactoryConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.factory.Dog;

public class TestFactorySpring {

    /**
     * @Title: test
     * @Description: 测试静态工厂方法
     * @param:
     * @return: void
     * @throws:
     */
    @Test
    public void testStatic() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-factory.xml");
        Dog dog = (Dog) context.getBean("dog1");
        System.out.println(dog);
    }

    @Test
    public void testInstance() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-factory.xml");
        Dog dog = (Dog) context.getBean("dog2");
        System.out.println(dog);
    }

    @Test
    public void testFactoryBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryConfig.class);

        DogFactoryBean bean = context.getBean(DogFactoryBean.class);
        DogFactoryBean bean1 = context.getBean(DogFactoryBean.class);
        System.out.println(bean == bean1);

        Object bean3 = context.getBean("dogFactoryBean");
        System.out.println(bean3);
        // class com.myz.spring.factory.Dog
        System.out.println(bean3.getClass());
        // 加上BeanFactory的前缀 &, 就会得到 FactoryBean本身
        Object bean4 = context.getBean("&dogFactoryBean");
        System.out.println(bean4);
        // class com.myz.spring.factory.DogFactoryBean
        System.out.println(bean4.getClass());
    }
}
