/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.converter;

import com.alibaba.fastjson.JSON;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 双向映射转换功能  对象JSON化
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class JsonConfigConvert<T> extends BidirectionalConverter<T, String> {

    private static final Logger logger = LoggerFactory.getLogger(JsonConfigConvert.class);

    @Override
    public String convertTo(T source, Type<String> destinationType, MappingContext mappingContext) {
        logger.info("********************* JsonConfigConvert convertTo() ,source = {}, destinationType = {} ***********************", source, destinationType);

        return JSON.toJSONString(source.toString());
    }

    @Override
    public T convertFrom(String source, Type<T> destinationType, MappingContext mappingContext) {
        logger.info("********************* JsonConfigConvert convertFrom() ,source = {}, destinationType = {} ***********************", source, destinationType);

        return JSON.parseObject(source, destinationType.getRawType());
    }
}
