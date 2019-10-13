package com.myz.spring.test;

import com.myz.spring.annotation.AnnotationScanConfig;
import com.myz.spring.annotation.PersonImp;
import com.myz.spring.annotation.School;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.annotation.Person;

/**
 * @author maoyz0621
 */
public class TestAnnotationSpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAnnotationSpring.class);

    @Test
    public void testAOP() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-annotation.xml");
        Person p = (Person) context.getBean("person");
        p.eat();
        System.out.println(p);

    }

    @Test
    public void testAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationScanConfig.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            LOGGER.debug("beanDefinitionNames = {}", name);
        }
        Person bean = context.getBean(Person.class);
        Person bean1 = context.getBean(Person.class);
        System.out.println(bean1 == bean);

        School school = context.getBean(School.class);
        School school1 = context.getBean(School.class);
        System.out.println(school == school1);
    }

}
