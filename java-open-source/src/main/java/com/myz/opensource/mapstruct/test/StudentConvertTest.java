package com.myz.opensource.mapstruct.test;

import com.myz.opensource.mapstruct.convert.factory.MapperConverterFactory;
import com.myz.opensource.mapstruct.convert.StudentConvert;
import com.myz.opensource.mapstruct.dto.StudentDTO;
import com.myz.opensource.mapstruct.entity.StudentEntity;
import org.junit.Test;

/**
 * @author maoyz
 * @version V1.0
 * @date 2022/1/7 18:23
 */
public class StudentConvertTest {

    @Test
    public void to() {
        StudentEntity studentEntity = new StudentEntity()
                .setWeight("123a");
        studentEntity.setId(2L);
        StudentDTO to = MapperConverterFactory.INSTANCE(StudentConvert.class).to(studentEntity);
        System.out.println(to + ":" + to.getId());
    }

    @Test
    public void back() {
        StudentDTO studentDTO = new StudentDTO()
                .setWeight("123");
        studentDTO.setId(1L);

        StudentEntity back = MapperConverterFactory.INSTANCE(StudentConvert.class).back(studentDTO);
        System.out.println(back + ":" + back.getId());
    }
}
