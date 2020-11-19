/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.custommapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.Date;

/**
 * mapAtoB 和 mapBtoA 方法用于双向映射转换功能
 *
 * @author maoyz0621 on 19-5-13
 * @version: v1.0
 */
public class DateCustomMapper extends CustomMapper<Source, Destination> {

    @Override
    public void mapAtoB(Source source, Destination destination, MappingContext context) {
        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        destination.setTimeStamp(source.getDate().getTime());

    }

    @Override
    public void mapBtoA(Destination destination, Source source, MappingContext context) {
        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        source.setDate(new Date(destination.getTimeStamp()));
    }
}
