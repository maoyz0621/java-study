package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.convert.factory.IPairConverter;
import com.myz.opensource.mapstruct.dto.StudentDTO;
import com.myz.opensource.mapstruct.entity.StudentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * @author maoyz
 * @version V1.0
 * @date 2022/1/7 18:22
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StudentConvert extends IPairConverter<StudentEntity, StudentDTO> {
}
