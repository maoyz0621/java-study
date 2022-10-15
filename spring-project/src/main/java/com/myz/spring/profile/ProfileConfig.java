/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * 并不能读取静态文件，只能加载类似于mysqlURL = ${db.user}/${db.password}的属性值。
 * 基于Spring解析获取 properties 文件单个属性值的方式，
 *
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com.myz.spring.profile")
public class ProfileConfig {

    /**
     * @return
     * @Bean方法在@Configuration 类中声明
     */

    @Bean
    @Profile("dev")
    public ProfileBean profileBeanDev() {
        System.out.println("=============== dev =============");
        return new ProfileBean();
    }


    @Bean
    @Profile("test")
    public ProfileBean profileBeanTest() {
        System.out.println("=============== test =============");
        return new ProfileBean();
    }

    @Bean
    public PropertiesUtils propertiesUtils() {
        System.out.println("=============== propertiesUtils =============");
        return new PropertiesUtils();
    }

}