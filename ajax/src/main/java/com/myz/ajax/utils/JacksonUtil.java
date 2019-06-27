/**
 * Copyright 2019 Inc.
 **/
package com.myz.ajax.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * jackson 工具类
 * @author maoyz0621 on 19-6-27
 * @version: v1.0
 */
public class JacksonUtil {

    /**
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
     * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
     * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
     * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
     * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
     * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
     */

    /**
     * bean中包含null
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * bean中剔除null
     */
    private static ObjectMapper nullMapper = new ObjectMapper();

    static {
        config();
        configNull();
    }

    private static void config() {
        // 解决实体未包含字段反序列化时抛出异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private static void configNull() {
        nullMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        nullMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        nullMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        nullMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        nullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 将一个object转换为json, 可以使一个java对象，也可以使集合
     */
    public static String objectToJson(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
     */

    /**
     * 将json结果集转化为对象
     */
    public static <T> T jsonToClass(String json, Class<T> beanType) {
        T t = null;
        try {
            t = mapper.readValue(json, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将json数据转换成Map
     */
    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将json数据转换成list
     */
    public static <T> List<T> jsonToList(String json, Class<T> beanType) {
        List<T> list = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
            list = mapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取json对象数据的属性
     */
    public static String findValue(String resData, String resPro) {
        String result = null;
        try {
            JsonNode node = mapper.readTree(resData);
            JsonNode resProNode = node.get(resPro);
            result = objectToJson(resProNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JsonNode readTree(String json) {
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonNode;
    }

    public static <T> String writeValueAsString(T json) {
        String jsonNode = null;
        try {
            jsonNode = mapper.writeValueAsString(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonNode;
    }
}