/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.mapstruct.utils;

import com.alibaba.fastjson.JSON;
import com.myz.opensource.mapstruct.entity.CarDiffTypeDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

/**
 * @author maoyz0621 on 2022/5/5
 * @version v1.0
 */
public class JsonUtils {

    @Named("toJson")
    public static String toJson(Object dto) {
        if (dto == null) {
            return null;
        }
        return JSON.toJSONString(dto);
    }

    @Named("fromJson")
    public static CarDiffTypeDTO fromJson(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }
        try {
            return JSON.parseObject(val, CarDiffTypeDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}