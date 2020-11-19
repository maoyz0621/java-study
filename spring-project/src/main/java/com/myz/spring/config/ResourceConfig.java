package com.myz.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author maoyz on 18-3-6.
 */
@Configuration
@ImportResource("classpath*:user.xml")
public class ResourceConfig {

    @Value("${user.username}")
    private String username;

    @Value("${user.age}")
    private String age;

    @Bean
    public User myUser() {
        return new User(username, age);
    }
}
