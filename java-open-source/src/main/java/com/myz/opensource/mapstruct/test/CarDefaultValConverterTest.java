/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.CarDefaultValConverter;
import com.myz.opensource.mapstruct.convert.MapperConverterFactory;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import com.myz.opensource.mapstruct.entity.CarType;
import org.junit.Test;

import java.util.Date;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class CarDefaultValConverterTest {

    @Test
    public void testto() {
        Car car = new Car();
        car.setNumberOfSeats(1);
        car.setType(new CarType("abc"));
        car.setCarType(new CarType("carType"));
        car.setUpdateTime(new Date());
        CarDiffTypeDTO to = MapperConverterFactory.INSTANCE(CarDefaultValConverter.class).to(car);
        System.out.println(to);
    }

    @Test
    public void testback() {
        CarDiffTypeDTO carDiffTypeDTO = new CarDiffTypeDTO();
        carDiffTypeDTO.setCreateTime("2021-08-21 17:33:02");
        carDiffTypeDTO.setUpdateTime("2021-08-21 17:33:02");
        Car car = MapperConverterFactory.INSTANCE(CarDefaultValConverter.class).back(carDiffTypeDTO);
        System.out.println(car);
    }
}