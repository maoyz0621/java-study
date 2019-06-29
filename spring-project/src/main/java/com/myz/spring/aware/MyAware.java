package com.myz.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 同时实现ApplicationContextAware和BeanNameAware
 *
 * @author maoyz on 18-3-6.
 */
public class MyAware implements ApplicationContextAware, BeanNameAware, BeanFactoryAware {

    private String beanName;

    /**
     * 后执行
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyAware setApplicationContext:" + applicationContext.getBean(this.beanName).hashCode());
    }

    /**
     * 先执行
     */
    @Override
    public void setBeanName(String name) {
        if ("myAware".equals(name)) {
            this.beanName = name;
            System.out.println("MyAware setBeanName:" + name + "--> " + name.hashCode());
        } else {
            this.beanName = name;
            System.out.println("MyAware setBeanName: xxxxxxxxxxxxxxxxxxxxxx");
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyAware setBeanFactory:" + beanFactory.getBean("myAware", MyAware.class).hashCode());
    }
}
