/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author maoyz0621 on 2022/6/6
 * @version v1.0
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> targetClz) {
        T beanInstance = null;
        //优先按type查
        try {
            beanInstance = (T) applicationContext.getBean(targetClz);
        } catch (Exception e) {
        }
        //按name查
        if (beanInstance == null) {
            String simpleName = targetClz.getSimpleName();
            //首字母小写
            simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            beanInstance = (T) applicationContext.getBean(simpleName);
        }
        if (beanInstance == null) {
            throw new RuntimeException("Component " + targetClz + " can not be found in Spring Container");
        }
        return beanInstance;
    }

    public static Object getBean(String clazz) {
        return ApplicationContextHelper.applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return ApplicationContextHelper.applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType, Object... params) {
        return ApplicationContextHelper.applicationContext.getBean(requiredType, params);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取当前profile,默认获取第一个
     */
    public static String getActiveProfile() {
        Environment environment = applicationContext.getEnvironment();
        return environment.getActiveProfiles().length == 0 ?
                environment.getDefaultProfiles()[0] : environment.getActiveProfiles()[0];
    }

    /**
     * 获取指定注解的Bean
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annType) {
        return applicationContext.getBeansWithAnnotation(annType);
    }

    /**
     * 从Spring容器中获取指定类型的Bean
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
}