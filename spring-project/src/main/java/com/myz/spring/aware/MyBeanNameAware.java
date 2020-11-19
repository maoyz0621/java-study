package com.myz.spring.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * BeanNameAware
 *
 * @author maoyz on 18-3-6.
 */
public class MyBeanNameAware implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("MyBeanNameAware:" + name + "-->" + name.hashCode());
    }
}
