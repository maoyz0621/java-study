/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们可以选择BoundMapperFacade 代替缺省性能较慢的 MapperFacade 类
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class OrikaMapTest {

    // 创建MapperFactory
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    /**
     * map映射
     */
    @Test
    public void test() {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("first", "aaaaaa");
        nameMap.put("last", "111111111");
        mapperFactory.classMap(PersonNameMap.class, PersonNameParts.class)
                .field("nameMap['first']", "firstName")
                .field("nameMap[\"last\"]", "lastName")
                .register();
        MapperFacade facade = mapperFactory.getMapperFacade();
        PersonNameMap personNameMap = new PersonNameMap(nameMap);

        PersonNameParts nameParts = facade.map(personNameMap, PersonNameParts.class);
        System.out.println(nameParts);
    }


}
