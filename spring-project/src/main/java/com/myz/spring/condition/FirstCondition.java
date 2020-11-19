/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
public class FirstCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // class加载器
        ClassLoader classLoader = context.getClassLoader();

        // 环境
        Environment environment = context.getEnvironment();

        // bean定义注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        // 资源
        ResourceLoader resourceLoader = context.getResourceLoader();

        return false;
    }
}
