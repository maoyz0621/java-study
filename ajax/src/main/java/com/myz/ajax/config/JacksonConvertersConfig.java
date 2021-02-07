/**
 * Copyright 2019 Inc.
 **/
package com.myz.ajax.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author maoyz0621 on 19-6-27
 * @version: v1.0
 */
// @Configuration
public class JacksonConvertersConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new JacksonHttpMessageConverter());
    }

}
