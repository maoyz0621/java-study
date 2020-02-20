/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.converter;

import com.myz.opensource.orika.BeanCopyUtil;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 * 可以选择BoundMapperFacade 代替缺省性能较慢的 MapperFacade 类
 * <p>
 * BidirectionalConverter
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class OrikaConverterTest {

    @Test
    public void test0() {
        MapperFactory mapperFactory = BeanCopyUtil.factory();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        // 自定义类型转换  全局  全局方式注册：
        converterFactory.registerConverter("jsonConvert", new JsonConfigConvert<BookInfo>());

        mapperFactory.classMap(BookEntity.class, BookDTO.class)
                .field("authorName", "author.name")
                .field("authorBirthday", "author.birthday")
                .fieldMap("bookInformation", "bookInfo").converter("jsonConvert").add()
                .byDefault()
                .register();

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        BookEntity bookEntity = new BookEntity(
                "Harry",
                "maoyz",
                Date.from(LocalDate.of(1952, Month.MARCH, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "{\"isbn\": \"9787532754687\", \n \"page\": 279\n }");

        final BookDTO bookDTO = mapperFacade.map(bookEntity, BookDTO.class);
        System.out.println(bookDTO);
    }

}
