/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author maoyz0621 on 19-5-13
 * @version: v1.0
 */
public class BeanCopyUtil {

    public static MapperFactory factory() {
        return new DefaultMapperFactory.Builder()
                .useAutoMapping(false)
                .mapNulls(false)
                .build();
    }
}
