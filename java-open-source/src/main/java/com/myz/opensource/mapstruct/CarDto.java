/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.mapstruct;

import com.myz.opensource.mapstruct.dto.CarType;
import lombok.Data;

/**
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
@Data
public class CarDto {
    private String make;
    /**
     * 名称不一致
     */
    private int seatCount;
    /**
     * 类型不一致
     * 基本类型的包装类型和String类型之间
     */
    private Double weight;
    /**
     * 类型不一致
     */
    private String type;

    private CarType carType;
}