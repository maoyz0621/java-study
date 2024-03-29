/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.factory.MapperConverterFactory;
import com.myz.opensource.mapstruct.convert.qualifiedBy.CarQualifiedByConverter;
import com.myz.opensource.mapstruct.dto.CarQualified;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import org.junit.Test;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public class CarQualifiedByConverterTest {

    @Test
    public void to() {
        Car car = new Car();
        car.setWeight("{\"createTime\":\"2021-08-21 17:33:02\",\"make\":\"mao\",\"updateTime\":\"2021-08-21 17:33:02\"}");
        CarQualified to = MapperConverterFactory.INSTANCE(CarQualifiedByConverter.class).to(car);
        System.out.println(to.getWeight());
    }

    @Test
    public void back() {
        CarQualified qualified = new CarQualified();
        CarDiffTypeDTO carDiffTypeDTO = new CarDiffTypeDTO();
        carDiffTypeDTO.setCreateTime("2021-08-21 17:33:02");
        carDiffTypeDTO.setUpdateTime("2021-08-21 17:33:02");
        carDiffTypeDTO.setMake("123456");
        qualified.setWeight(carDiffTypeDTO);
        Car back = MapperConverterFactory.INSTANCE(CarQualifiedByConverter.class).back(qualified);
        System.out.println(back.getWeight());
    }
}