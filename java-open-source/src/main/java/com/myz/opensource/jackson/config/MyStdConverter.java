package com.myz.opensource.jackson.config;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * String -> Long
 * @author maoyz
 * @version V1.0
 * @date 2021/9/2 19:59
 */
public class MyStdConverter extends StdConverter<String, Long> {

    @Override
    public Long convert(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Long.parseLong(value);
    }
}
