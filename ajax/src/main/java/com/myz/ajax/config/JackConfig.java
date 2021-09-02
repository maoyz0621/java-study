/**
 * Copyright 2019 Inc.
 **/
package com.myz.ajax.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author maoyz0621 on 19-6-26
 * @version: v1.0
 */
//@Configuration
public class JackConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        ObjectMapper build = jackson2ObjectMapperBuilder.createXmlMapper(false).build();
        build.setSerializerFactory(build.getSerializerFactory().withSerializerModifier(new _BeanSerializerModifier()));
        return build;
    }

}
