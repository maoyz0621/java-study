/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.list;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

import java.util.Arrays;

/**
 * 我们可以选择BoundMapperFacade 代替缺省性能较慢的 MapperFacade 类
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class OrikaListTest {

    // 创建MapperFactory
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    /**
     * 索引映射
     */
    @Test
    public void test() {
        mapperFactory.classMap(PersonNameList.class, PersonNameParts.class)
                .field("nameLists[0]", "firstName")
                .field("nameLists[1]", "lastName")
                .register();
        MapperFacade facade = mapperFactory.getMapperFacade();
        PersonNameList personNameList = new PersonNameList(Arrays.asList("aaaaa", "1111111"));

        PersonNameParts nameParts = facade.map(personNameList, PersonNameParts.class);
        System.out.println(nameParts);
    }


}
