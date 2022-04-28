/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.mapstruct.convert.multi;

import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDto;
import com.myz.opensource.mapstruct.entity.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
@Mapper(componentModel = "spring")
public interface CarMultSourceConverter {

    /**
     * 多个参数中的值绑定
     *
     * @param source1 源1
     * @param source2 源2
     * @return 从源1、2中提取出的结果
     */
    @Mappings({
            @Mapping(source = "source1.numberOfSeats", target = "seatCount"),
            @Mapping(source = "source2.type", target = "type"),
            @Mapping(source = "source2", target = "carType")
    })
    CarDto car2CarDto(Car source1, CarType source2);
}