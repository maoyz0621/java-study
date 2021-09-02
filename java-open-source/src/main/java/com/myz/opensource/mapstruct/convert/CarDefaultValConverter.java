package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设置默认值
 *
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:25
 */
@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CarDefaultValConverter extends IPairConverter<Car, CarDiffTypeDTO> {
    /**
     * 定义时间格式化
     */
    @Mappings({
            @Mapping(target = "make", constant = "ABC"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss", defaultExpression = "java(LocalDateTime.now().toString())"),
            @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    @Override
    CarDiffTypeDTO to(Car src);

    @Override
    List<CarDiffTypeDTO> to(List<Car> srcList);

    @InheritInverseConfiguration
    @Override
    Car back(CarDiffTypeDTO dest);

    @Override
    List<Car> back(List<CarDiffTypeDTO> destList);
}
