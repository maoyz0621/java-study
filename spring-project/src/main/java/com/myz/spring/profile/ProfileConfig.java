/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.profile;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringValueResolver;

/**
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
@Configuration
public class ProfileConfig implements EmbeddedValueResolverAware {

    @Profile("dev")
    @Bean
    public ProfileBean profileBeanDev() {
        System.out.println("=============== dev =============");
        return new ProfileBean();
    }

    @Profile("test")
    @Bean
    public ProfileBean profileBeanTest() {
        System.out.println("=============== test =============");
        return new ProfileBean();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("");
    }
}
