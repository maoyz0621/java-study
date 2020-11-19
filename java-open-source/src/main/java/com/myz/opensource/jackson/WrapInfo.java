/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

/**
 * 包装类型
 *
 * @author maoyz0621 on 2020/9/28
 * @version v1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WrapInfo {

    private Integer age;
    private int height;
    /**
     * boolean 类型
     */
    private Boolean sex;
    private boolean sex1;

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "{\"age\":20,\"height\":0,\"sex\":\"true\"}";
        WrapInfo personInfo = objectMapper.readValue(result, WrapInfo.class);

        // WrapInfo(age=20, height=0, sex=true, sex1=false)
        System.out.println(personInfo);
        // {"age":20,"height":0,"sex":true,"sex1":false}
        System.out.println(objectMapper.writeValueAsString(personInfo));
    }
}