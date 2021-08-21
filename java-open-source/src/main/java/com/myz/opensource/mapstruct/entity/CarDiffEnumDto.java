/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.mapstruct.entity;

import lombok.Data;

/**
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
@Data
public class CarDiffEnumDto {
    private String make;
    /**
     * 枚举类
     */
    private String carEnum;
}