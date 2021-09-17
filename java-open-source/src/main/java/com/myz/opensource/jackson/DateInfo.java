/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.util.Date;

/**
 * Date 类型
 *
 * @author maoyz0621 on 2020/9/28
 * @version v1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateInfo {

    /**
     * 日期格式 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty("date")
    private Date date;

    /**
     * 时间格式 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("time")
    private Date time;

    /**
     * boolean 类型
     */
    private Boolean sex;

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "{\"age\":20,\"height\":0,\"date\":\"2020-08-28\",\"time\":\"2020-09-28 11:11:21\",\"localTime\":\"2020-08-28 11:11:11\",\"sex\":\"true\"}";
        DateInfo personInfo = objectMapper.readValue(result, DateInfo.class);

        // DateInfo(date=Fri Aug 28 08:00:00 CST 2020, time=Mon Sep 28 19:11:21 CST 2020, sex=true)
        System.out.println(personInfo);
        // {"sex":true,"date":"2020-08-28","time":"2020-09-28 11:11:21"}
        System.out.println(objectMapper.writeValueAsString(personInfo));
    }
}