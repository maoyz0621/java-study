/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * LocalDate
 *
 * @author maoyz0621 on 2020/9/28
 * @version v1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalDateInfo {
    /**
     * 字段属性为LocalDate
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("localDay")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate localDay;

    /**
     * 字段属性为LocalDateTime
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("localTime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime localTime;

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "{\"age\":20,\"height\":0,\"date\":\"2020-08-28\",\"time\":\"2020-09-28 11:11:21\",\"localDay\":\"2020-09-28\",\"localTime\":\"2020-08-28 11:11:11\",\"sex\":\"true\"}";
        LocalDateInfo personInfo = objectMapper.readValue(result, LocalDateInfo.class);

        // LocalDateInfo(localDay=2020-09-28, localTime=2020-08-28T11:11:11)
        System.out.println(personInfo);
        // {"localDay":"2020-09-28","localTime":"2020-08-28 11:11:11"}
        System.out.println(objectMapper.writeValueAsString(personInfo));
    }
}