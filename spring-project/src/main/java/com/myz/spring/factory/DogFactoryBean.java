/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
// import org.springframework.lang.Nullable;

/**
 * FactoryBean
 * @author maoyz0621 on 19-10-10
 * @version: v1.0
 */
public class DogFactoryBean implements FactoryBean<Dog>, InitializingBean {

    // @Nullable
    private Dog target;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.target = this.target == null ? new Dog() : target;
    }

    @Override
    public Dog getObject() throws Exception {
        return this.target;
    }

    @Override
    public Class<?> getObjectType() {
        return Dog.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
