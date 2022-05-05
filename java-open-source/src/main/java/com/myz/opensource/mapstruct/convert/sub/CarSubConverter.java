package com.myz.opensource.mapstruct.convert.sub;

import com.myz.opensource.mapstruct.convert.factory.IPairConverter;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarSubDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:25
 */
@Mapper(componentModel = "spring")
public interface CarSubConverter extends IPairConverter<Car, CarSubDto> {
    CarSubConverter INSTANCE = Mappers.getMapper(CarSubConverter.class);

    /**
     * 子对象
     *
     * @param src
     * @return
     */
    @Mappings({
            @Mapping(source = "type.type", target = "type")
    })
    @Override
    CarSubDto to(Car src);

    @Mappings({
            // @Mapping(source = "type", target = "type.type", ignore = true)
            @Mapping(source = "type", target = "type.type")
    })
    @Override
    Car back(CarSubDto dest);
}