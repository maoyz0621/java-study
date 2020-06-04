package com.myz.spring.test;

import com.myz.spring.config.AppConfig;
import com.myz.spring.config.MyBean;
import com.myz.spring.config.ResourceConfig;
import com.myz.spring.config.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * /@ImportResource("classpath*:user.xml")和@Value()
 *
 * @author maoyz
 */
public class TestConfigSpring {

    /**
     * AnnotationConfig
     * ApplicationContext
     * 扫描@Configuration
     */
    @Test
    public void testConfig() {
        // AnnotationConfigApplicationContext和@Configuration配合使用
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyBean myBean = (MyBean) context.getBean("myBean");
        System.out.println(myBean.getClass().getName());
        context.close();
    }

    /**
     * /@ImportResource("classpath*:user.xml")
     */
    @Test
    public void testResource() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ResourceConfig.class);
        User user = (User) context.getBean("myUser");
        System.out.println(user.getClass().getName());
    }

    /**
     * /@Scope
     */
    @Test
    public void testScope() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyBean myBean1 = (MyBean) context.getBean("myBean");
        MyBean myBean2 = (MyBean) context.getBean("myBean");
        System.out.println(myBean1 == myBean2);
    }

}
