/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * todo BeanDefinitionRegistryPostProcessor 优先于 BeanFactoryPostProcessor执行
 * @author maoyz0621 on 19-10-12
 * @version: v1.0
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 先执行
     *
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry() " + registry.getBeanDefinitionCount());

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition("").getBeanDefinition();
        registry.registerBeanDefinition("", beanDefinition);
    }

    /**
     * 后执行
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor postProcessBeanFactory() " + beanFactory.getSingletonCount());
    }
}
