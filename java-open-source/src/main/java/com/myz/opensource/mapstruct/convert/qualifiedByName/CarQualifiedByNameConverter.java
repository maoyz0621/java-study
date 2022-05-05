package com.myz.opensource.mapstruct.convert.qualifiedByName;

import com.alibaba.fastjson.JSON;
import com.myz.opensource.mapstruct.convert.factory.IPairConverter;
import com.myz.opensource.mapstruct.dto.CarQualified;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:25
 */
@Mapper(componentModel = "spring")
public interface CarQualifiedByNameConverter extends IPairConverter<Car, CarQualified> {
    CarQualifiedByNameConverter INSTANCE = Mappers.getMapper(CarQualifiedByNameConverter.class);

    /**
     * 子对象
     *
     * @param src
     * @return
     */
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

    @Named("toJson")
    default String toJson(CarDiffTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        return JSON.toJSONString(dto);
    }

    @Named("fromJson")
    default CarDiffTypeDTO fromJson(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }
        try {
            return JSON.parseObject(val, CarDiffTypeDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}