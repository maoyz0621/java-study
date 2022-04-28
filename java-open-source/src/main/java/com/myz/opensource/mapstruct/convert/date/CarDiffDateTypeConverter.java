package com.myz.opensource.mapstruct.convert.date;

import com.myz.opensource.mapstruct.convert.factory.IPairConverter;
import com.myz.opensource.mapstruct.entity.Car;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 不同日期类型键相互转换
 *
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:25
 */
@Mapper(componentModel = "spring")
public interface CarDiffDateTypeConverter extends IPairConverter<Car, CarDiffTypeDTO> {
    CarDiffDateTypeConverter INSTANCE = Mappers.getMapper(CarDiffDateTypeConverter.class);

    /**
     * 定义时间格式化
     *
     * @param src
     * @return
     */
    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    @Override
    CarDiffTypeDTO to(Car src);

    @Override
    List<CarDiffTypeDTO> to(List<Car> srcList);

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    @Override
    Car back(CarDiffTypeDTO dest);

    @Override
    List<Car> back(List<CarDiffTypeDTO> destList);
}
