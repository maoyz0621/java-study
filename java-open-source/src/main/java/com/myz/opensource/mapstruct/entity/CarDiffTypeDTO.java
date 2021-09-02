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
public class CarDiffTypeDTO {
    private String make;
    /**
     * 类型不一致
     */
    private String createTime;
    private String updateTime;
}