/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.custommapper;

import com.myz.opensource.orika.BeanCopyUtil;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.junit.Test;

import java.util.Date;

/**
 * CustomMapper  .customize()定制
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class OrikaCustommapperTest {

    /**
     * 自定义类型转换
     */
    @Test
    public void test() {
        MapperFactory mapperFactory = BeanCopyUtil.factory();
        mapperFactory.classMap(Source.class, Destination.class)
                .customize(new DateCustomMapper())
                .field("date", "timeStamp")
                .byDefault()
                .register();
        MapperFacade facade = mapperFactory.getMapperFacade();

        Source source = new Source("aaaaa", new Date());
        Destination destination = null;
        try {
            destination = facade.map(source, Destination.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(destination);

        System.out.println("*********************************************");

        Destination destination1 = new Destination("bbbbbbbb", System.currentTimeMillis());
        Source map = facade.map(destination1, Source.class);
        System.out.println(map);
    }
}
