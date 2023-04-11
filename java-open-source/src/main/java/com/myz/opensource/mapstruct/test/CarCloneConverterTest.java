/**
 * Copyright 2023 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.CarCloneConverter;
import com.myz.opensource.mapstruct.convert.factory.MapperConverterFactory;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author maoyz0621 on 2023/3/21
 * @version v1.0
 */
@Slf4j
public class CarCloneConverterTest {

    @Test
    public void testClone() {
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        car.setType(new CarType("abc"));
        car.setCarType(new CarType("carType"));
        car.setCreateTime(LocalDateTime.now());
        car.setUpdateTime(new Date());
        log.info("car={}", car.hashCode());
        Car clone = MapperConverterFactory.INSTANCE(CarCloneConverter.class).clone(car);
        log.info("clone={}", clone.hashCode());


        car.setMake("mao123");
        car.setNumberOfSeats(2);
        log.info("car, {}, {}", car.getMake(), car.getNumberOfSeats());
        log.info("clone, {}, {}", clone.getMake(), clone.getNumberOfSeats());
    }
}