package com.myz.opensource.jackson.config;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/2 19:59
 */
public class MySecStdConverter extends StdConverter<String, String> {

    @Override
    public String convert(String value) {
        return "*";
    }
}
