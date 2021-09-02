/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.mapstruct.dto;

/**
 * @author maoyz0621 on 2021/8/20
 * @version v1.0
 */
public enum CarEnum {
    CAR("1", "汽车"),
    BIKE("2", "自行车");

    private final String code;
    private final String name;

    CarEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}