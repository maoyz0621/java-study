/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.CarInheritInverseConfigurationMapper;
import com.myz.opensource.mapstruct.convert.MapperConverterFactory;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDto;
import com.myz.opensource.mapstruct.entity.CarType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class CarInheritInverseConfigurationTest {

    @Test
    public void testto() {
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        car.setType(new CarType("abc"));
        car.setCarType(new CarType("carType"));
        car.setCreateTime(LocalDateTime.now());
        car.setUpdateTime(new Date());
        CarDto to = MapperConverterFactory.INSTANCE(CarInheritInverseConfigurationMapper.class).to(car);
        System.out.println(to);
    }

    @Test
    public void testback() {
        CarDto carDto = new CarDto();
        carDto.setMake("abc");
        carDto.setSeatCount(2);
        Car car = MapperConverterFactory.INSTANCE(CarInheritInverseConfigurationMapper.class).back(carDto);
        System.out.println(car);
    }
}