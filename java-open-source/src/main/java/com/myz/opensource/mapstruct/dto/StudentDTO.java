package com.myz.opensource.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author maoyz
 * @version V1.0
 * @date 2022/1/7 18:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class StudentDTO extends BaseDTO {

    private String weight;
}
