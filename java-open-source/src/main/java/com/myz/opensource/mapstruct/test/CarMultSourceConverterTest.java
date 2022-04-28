/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.factory.MapperConverterFactory;
import com.myz.opensource.mapstruct.convert.multi.CarMultSourceConverter;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDto;
import com.myz.opensource.mapstruct.entity.CarType;
import org.junit.Test;

/**
 * @author maoyz0621 on 2021/8/21
 * @version v1.0
 */
public class CarMultSourceConverterTest {

    @Test
    public void test(){
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        CarType carType = new CarType("Audi");

        CarDto carDto = MapperConverterFactory.INSTANCE(CarMultSourceConverter.class).car2CarDto(car, carType);
        System.out.println(carDto);
    }
}