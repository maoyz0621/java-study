/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.convert;

import org.mapstruct.factory.Mappers;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class MapperConverterFactory {

    public static <T> T INSTANCE(Class<T> clazz) {
        return Mappers.getMapper(clazz);
    }

}