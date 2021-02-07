/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.mapstruct;

/**
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
public class MapStructTest {

    public static void main(String[] args) {
        // baseConvert();
        baseConvertTarget();
        // baseConvertParam();
    }

    private static void baseConvert() {
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        car.setType(new CarType("abc"));
        car.setCarType(new CarType("carType"));

        CarDto carDto = CarMapper.INSTANCE.car2CarDto(car);
        // true
        System.out.println(com.myz.opensource.mapstruct.dto.CarType.class == carDto.getCarType().getClass());
        System.out.println(carDto);
    }

    private static void baseConvertTarget() {
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        car.setType(new CarType("abc"));
        car.setCarType(new CarType("carType"));

        CarDto carDto = new CarDto();
        CarMapper.INSTANCE.convertToCarDto(car, carDto);
        System.out.println(carDto);
    }

    private static void baseConvertParam() {
        Car car = new Car();
        car.setMake("mao");
        car.setNumberOfSeats(1);
        car.setWeight("123.123");
        CarType carType = new CarType("Audi");

        CarDto carDto = CarMapper.INSTANCE.car2CarDto(car, carType);
        System.out.println(carDto);
    }
}