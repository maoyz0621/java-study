/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.mapstruct.convert.qualifiedBy;

import com.myz.opensource.mapstruct.convert.factory.IPairConverter;
import com.myz.opensource.mapstruct.dto.CarQualified;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.utils.JsonUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author maoyz0621 on 2022/5/5
 * @version v1.0
 */
@Mapper(componentModel = "spring", uses = JsonUtils.class)
public interface CarQualifiedByConverter extends IPairConverter<Car, CarQualified> {

    @Mappings({
            @Mapping(source = "weight", target = "weight", qualifiedByName = "fromJson")
    })
    @Override
    CarQualified to(Car src);

    @Mappings({
            @Mapping(source = "weight", target = "weight", qualifiedByName = "toJson")
    })
    @Override
    Car back(CarQualified dest);
}