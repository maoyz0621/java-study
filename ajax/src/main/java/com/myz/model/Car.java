/**
 * Copyright 2020 Inc.
 **/
package com.myz.model;

import lombok.Data;

/**
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
@Data
public class Car {

    private String make;
    /**
     * 名称不一致
     */
    private int numberOfSeats;
    /**
     * 类型不一致
     * 基本类型的包装类型和String类型之间
     */
    private String weight;
    /**
     * 类型不一致，名称一致
     */
    private CarType type;
    /**
     * 类的包名不一样，名称一致
     */
    private CarType carType;
}