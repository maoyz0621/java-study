/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
@Configuration
@ComponentScan(value = "com.myz.spring.factory")
public class FactoryConfig {

    @Bean
    public DogFactoryBean dogFactoryBean() {
        return new DogFactoryBean();
    }
}
