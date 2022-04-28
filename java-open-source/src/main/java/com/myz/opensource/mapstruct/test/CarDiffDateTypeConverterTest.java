/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.date.CarDiffDateTypeConverter;
import com.myz.opensource.mapstruct.convert.factory.MapperConverterFactory;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import com.myz.opensource.mapstruct.entity.CarType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class CarDiffDateTypeConverterTest {

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
        CarDiffTypeDTO to = MapperConverterFactory.INSTANCE(CarDiffDateTypeConverter.class).to(car);
        System.out.println(to);
    }

    @Test
    public void testback() {
        CarDiffTypeDTO carDiffTypeDTO = new CarDiffTypeDTO();
        carDiffTypeDTO.setCreateTime("2021-08-21 17:33:02");
        carDiffTypeDTO.setUpdateTime("2021-08-21 17:33:02");
        Car car = MapperConverterFactory.INSTANCE(CarDiffDateTypeConverter.class).back(carDiffTypeDTO);
        System.out.println(car);
    }
}