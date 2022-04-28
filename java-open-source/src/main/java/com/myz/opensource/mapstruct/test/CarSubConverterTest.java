/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.sub.CarSubConverter;
import com.myz.opensource.mapstruct.convert.factory.MapperConverterFactory;
import com.myz.opensource.mapstruct.dto.CarEnum;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarSubDto;
import com.myz.opensource.mapstruct.entity.CarType;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class CarSubConverterTest {

    @Test
    public void testto() {
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        car.setType(new CarType("abc"));
        car.setCarType(new CarType("carType"));
        car.setCreateTime(LocalDateTime.now());
        car.setCarEnum(CarEnum.CAR);
        CarSubDto to = MapperConverterFactory.INSTANCE(CarSubConverter.class).to(car);
        // CarSubDto(make=mao, type=abc)
        System.out.println(to);
    }

    @Test
    public void testback() {
        CarSubDto carSubDto = new CarSubDto();
        carSubDto.setType("aaa");
        Car car = MapperConverterFactory.INSTANCE(CarSubConverter.class).back(carSubDto);
        // type=CarType(type=aaa)
        System.out.println(car);
    }
}