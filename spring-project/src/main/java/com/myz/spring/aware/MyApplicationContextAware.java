package com.myz.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContextAware
 * 对当前bean传入对应的Spring上下文,这个类可以直接获取Spring配置文件中，所有有引用到的bean对象。
 *
 * @author maoyz on 18-3-6.
 */
public class MyApplicationContextAware implements ApplicationContextAware {

    /**
     * 定义全局context
     */
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 将Spring上下文中的context传入该类中
        MyApplicationContextAware.context = applicationContext;
        System.out.println("MyApplicationContextAware:" + applicationContext.getBean("myApplicationContextAware").hashCode());
    }

    /**
     * 对外提供访问接口
     */
    public static ApplicationContext getContext() {
        return context;
    }
}
