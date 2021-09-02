package com.myz.opensource.jackson.config;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 日期转Long型
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/2 20:25
 */
public class DateToLongStdConverter extends StdConverter<String, Long> {

    @Override
    public Long convert(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(value, formatter);
        return parse.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}