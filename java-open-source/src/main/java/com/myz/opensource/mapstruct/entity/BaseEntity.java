package com.myz.opensource.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author maoyz
 * @version V1.0
 * @date 2022/1/7 18:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseEntity {

    private Long id;
}
