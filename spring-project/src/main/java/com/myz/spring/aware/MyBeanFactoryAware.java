/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * BeanFactoryAware
 *
 * @author maoyz0621 on 19-2-17
 * @version: v1.0
 */
public class MyBeanFactoryAware implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryAware :" + beanFactory.getBean("myBeanFactoryAware", MyBeanFactoryAware.class).hashCode());
    }
}
