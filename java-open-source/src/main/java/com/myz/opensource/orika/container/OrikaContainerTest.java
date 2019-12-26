/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.container;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

import java.util.Date;

/**
 * 我们可以选择BoundMapperFacade 代替缺省性能较慢的 MapperFacade 类
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class OrikaContainerTest {

    // 创建MapperFactory
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    /**
     * 映射嵌套字段
     */
    @Test
    public void test() {
        mapperFactory.classMap(PersonContainer.class, PersonNameParts.class)
                .field("name.firstName", "firstName")
                .field("name.lastName", "lastName")
                .field("name.birth", "birth")
                .register();
        MapperFacade facade = mapperFactory.getMapperFacade();
        PersonContainer personNameList = new PersonContainer(new Name("aaaaa", "1111111", new Date()));

        PersonNameParts nameParts = facade.map(personNameList, PersonNameParts.class);
        System.out.println(nameParts);
    }


}
