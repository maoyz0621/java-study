/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.CarDiffEnumConverter;
import com.myz.opensource.mapstruct.convert.MapperConverterFactory;
import com.myz.opensource.mapstruct.dto.CarEnum;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffEnumDto;
import com.myz.opensource.mapstruct.entity.CarType;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class CarDiffEnumConverterTest {

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
        CarDiffEnumDto to = MapperConverterFactory.INSTANCE(CarDiffEnumConverter.class).to(car);
        // carEnum=CAR
        // getCarEnum().name()
        System.out.println(to);
    }

    @Test
    public void testback() {
        CarDiffEnumDto carDiffTypeDTO = new CarDiffEnumDto();
        carDiffTypeDTO.setCarEnum(CarEnum.CAR.name());
        Car car = MapperConverterFactory.INSTANCE(CarDiffEnumConverter.class).back(carDiffTypeDTO);
        System.out.println(car);
    }
}