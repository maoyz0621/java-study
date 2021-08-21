package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffEnumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:25
 */
@Mapper(componentModel = "spring")
public interface CarDiffEnumConverter extends IPairConverter<Car, CarDiffEnumDto> {

    @Mappings({
            @Mapping(source = "carEnum.code", target = "carEnum")
    })
    @Override
    CarDiffEnumDto to(Car src);


    @Override
    List<CarDiffEnumDto> to(List<Car> srcList);

    @Mappings({
            @Mapping(source = "carEnum", target = "carEnum")
    })
    @Override
    Car back(CarDiffEnumDto dest);


    @Override
    List<Car> back(List<CarDiffEnumDto> destList);
}
