/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDto;
import com.myz.opensource.mapstruct.entity.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 定义这是一个MapStruct对象属性转换接口，在这个类里面规定转换规则
 * 在项目构建时，会自动生成改接口的实现类，这个实现类将实现对象属性值复制
 * componentModel = "spring" 使用Spring依赖注入环境
 *
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

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
    CarDto car2CarDto(Car source);

    /**
     * 集合形式
     *
     * @param source
     * @return
     */
    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type.type", target = "type")
    })
    List<CarDto> car2CarDto(List<Car> source);


    @Mappings({
            @Mapping(source = "seatCount", target = "numberOfSeats"),
            @Mapping(source = "type", target = "type.type")
    })
    Car carDto2car(CarDto carDto);

    @Mappings({
            @Mapping(source = "seatCount", target = "numberOfSeats"),
            @Mapping(source = "type", target = "type.type")
    })
    List<Car> carDto2car(List<CarDto> carDto);

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

    /**
     * 通过@MappingTarget来指定目标类是谁（谁的指定属性需要被更新）。@Mapping还是用来定义属性对应规则。
     *
     * @param source
     * @param target
     */
    @Mappings({
            // @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type.type", target = "type")
    })
    void convertToCarDto(Car source, @MappingTarget CarDto target);


    @Mappings({
            // @Mapping(source = "seatCount", target = "numberOfSeats"),
            @Mapping(source = "type", target = "type.type")
    })
    void convertToCar(CarDto carDto, @MappingTarget Car car);

}