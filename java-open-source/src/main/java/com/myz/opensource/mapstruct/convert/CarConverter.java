package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.convert.factory.IPairConverter;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:25
 */
@Mapper(componentModel = "default")
public interface CarConverter extends IPairConverter<Car, CarDto> {

    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type.type", target = "type")
    })
    @Override
    CarDto to(Car src);

    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type.type", target = "type")
    })
    @Override
    List<CarDto> to(List<Car> srcList);

    @Mappings({
            @Mapping(source = "seatCount", target = "numberOfSeats"),
            @Mapping(source = "type", target = "type.type")
    })
    @Override
    Car back(CarDto dest);

    @Mappings({
            @Mapping(source = "seatCount", target = "numberOfSeats"),
            @Mapping(source = "type", target = "type.type")
    })
    @Override
    List<Car> back(List<CarDto> destList);
}
