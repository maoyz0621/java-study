/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.opensource.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fastJson 测试类
 * <p>
 * SerializeWriter：相当于StringBuffer
 * JSONArray：相当于List<Object>
 * JSONObject：相当于Map<String, Object>
 * JSON反序列化没有真正数组，本质类型都是List<Object>
 * <p>
 * JSONObject的数据是用 { } 来表示的，
 * JSONArray，顾名思义是由JSONObject构成的数组，用 [ { } , { } , …… , { } ] 来表示
 *
 * @author maoyz0621 on 2019/3/26
 * @version: v1.0
 */
public class FastJsonTest {

    /**
     * Map转JSON
     */
    @Test
    public void mapToJson() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("username", "maoyz");
        map.put("password", "123");
        // {password=123, username=maoyz}
        System.out.println(map.toString());

        JSONObject jsonObject = new JSONObject(map);
        // {"password":"123","username":"maoyz"}
        System.out.println(jsonObject);
        // {"password":"123","username":"maoyz"}
        System.out.println(jsonObject.toJSONString());
        // 底层toJSONString()  {"password":"123","username":"maoyz"}
        System.out.println(jsonObject.toString());
    }

    /**
     * JSON转Map
     */
    @Test
    public void JsonToMap() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "maoyz");
        jsonObject.put("password", "123456");
        Map<String, Object> map = jsonObject;

        // {"password":"123456","username":"maoyz"}
        System.out.println(map);

        map.forEach((k, v) -> System.out.println(k + ": " + v));


        Map javaObject = JSONObject.toJavaObject(jsonObject, Map.class);
        // {"password":"123456","username":"maoyz"}
        System.out.println(javaObject);
    }

    /**
     * JSON转String
     */
    @Test
    public void JsonToString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "maoyz");
        jsonObject.put("password", "123456");
        // {"password":"123456","username":"maoyz"}
        System.out.println(jsonObject.toJSONString());
    }

    /**
     * String 转 JSON
     */
    @Test
    public void StringToJson() {
        String str = "{\"username\":\"maoyz\",\"password\":\"123\"}";
        JSONObject json = JSONObject.parseObject(str);

        // {"password":"123","username":"maoyz"}
        System.out.println(json);
    }

    /**
     * String 转 Map
     */
    @Test
    public void StringToMap() {
        String str = "{\"username\":\"maoyz\",\"password\":\"123\"}";

        Map map = JSONObject.parseObject(str, Map.class);
        // {password=123, username=maoyz}
        System.out.println(map);
    }

    /**
     * Map 转 String
     */
    @Test
    public void mapToString() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("username", "maoyz");
        map.put("password", "123");
        String string = JSON.toJSONString(map);
        // {"password":"123","username":"maoyz"}
        System.out.println(string);

    }

    /**
     * 将List转换成JSONArray
     */
    @Test
    public void listToJSONArray() {
        // 解析Java  list
        String[] arrs = {"1", "2", "a"};
        List<String> list = Arrays.asList(arrs);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        // ["1","2","a"]
        System.out.println(jsonArray);


        // 解析[{} , {}] 数据类型
        String str = "[{\"username\":\"maoyz\",\"password\":\"123\"},{\"username\":\"aaaa\",\"password\":\"bbbb\"}]";
        JSONArray array = JSONArray.parseArray(str);
        for (Object o : array) {
            JSONObject jsonObject = (JSONObject) o;
            System.out.println(jsonObject.getString("username") + ":" + jsonObject.get("password"));
        }
        // [{"password":"123","username":"maoyz"},{"password":"bbbb","username":"aaaa"}]
        System.out.println(array);


        // 解析{     []} 数据类型
        String param = "{\"code\":\"111111111111\",\"money\":150, \"phoneList\":[\"17752516738\",\"13393719370\",\"15083387720\"]}";
        JSONObject json = JSON.parseObject(param);
        // {"code":"111111111111","money":150,"phoneList":["17752516738","13393719370","15083387720"]}
        System.out.println(json);
        String code = json.getString("code");
        Double money = json.getDouble("money");
        JSONArray phoneList = json.getJSONArray("phoneList");
        System.out.println("code = " + code + " ,money = " + money + " ,list = " + phoneList);
    }
}
