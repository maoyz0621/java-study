/**
 * Copyright 2019 Inc.
 **/
package com.myz.ajax.config;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * https://blog.csdn.net/qq_38132283/article/details/89339817
 *
 * @author maoyz0621 on 19-6-26
 * @version: v1.0
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public JacksonHttpMessageConverter() {
        getObjectMapper().setSerializerFactory(getObjectMapper().getSerializerFactory().withSerializerModifier(new _BeanSerializerModifier()));
    }

}

