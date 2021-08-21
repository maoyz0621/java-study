/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 继承逆向配置 @InheritInverseConfiguration
 *
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
@Mapper(componentModel = "spring")
public interface CarInheritInverseConfigurationMapper {

    CarInheritInverseConfigurationMapper INSTANCE = Mappers.getMapper(CarInheritInverseConfigurationMapper.class);

    /**
     * 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性
     *
     * @param source
     * @return CarDto
     */
    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type.type", target = "type")
    })
    CarDto to(Car source);

    List<CarDto> to(List<Car> source);

    @InheritInverseConfiguration
    Car back(CarDto carDto);

    List<Car> back(List<CarDto> carDto);

}