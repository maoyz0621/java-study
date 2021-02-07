/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将JSON转Map
 *
 * @author maoyz0621 on 2019/3/26
 * @version: v1.0
 */
public class JsonToMapUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonToMapUtil.class);

    /**
     * fastjson 解析
     */
    public static Map<String, Object> jsonConvertMap(ServletRequest request) throws IOException {
        String jsonString = IOUtils.toString(request.getInputStream(), "UTF-8");

        if (StringUtils.isEmpty(jsonString)) {
            throw new RuntimeException("传入param is null");
        }
        logger.debug("=============== JSON = {} ================", jsonString);

        ConcurrentHashMap map = JSONObject.parseObject(jsonString, ConcurrentHashMap.class);
        return map;
    }

    /**
     * jackson 解析
     */
    public static Map<String, Object> jsonConvertMap(String json) throws IOException {
        if (StringUtils.isEmpty(json)) {
            throw new RuntimeException("传入param is null");
        }
        logger.debug("=============== JSON = {} ================", json);

        return new ObjectMapper().readValue(json, Map.class);
    }
}
