/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author maoyz0621 on 2020/9/27
 * @version v1.0
 */
public class JacksonRead {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        String data = "{\n" +
                "\t\"msg\": \"success\",\n" +
                "\t\"code\": 0,\n" +
                "\t\"table\": {\n" +
                "\t\t\"index\": \"abc\"\n" +
                "\t},\n" +
                "\t\"context\": [{\n" +
                "\n" +
                "\t\t\"index\": \"123\"\n" +
                "\t}, {\n" +
                "\t\t\"index\": \"456\"\n" +
                "\t}]\n,\"inner\": {\"username\":\"inner\"}" +
                "}";

        String dataList = "[{\n" +
                "\t\"msg\": \"success\",\n" +
                "\t\"code\": 0,\n" +
                "\t\"table\": {\n" +
                "\t\t\"index\": \"abc\"\n" +
                "\t},\n" +
                "\t\"context\": [{\n" +
                "\n" +
                "\t\t\"index\": \"123\"\n" +
                "\t}, {\n" +
                "\t\t\"index\": \"456\"\n" +
                "\t}]\n" +
                "}, {\n" +
                "\t\"msg\": \"error\",\n" +
                "\t\"code\": 0,\n" +
                "\t\"table\": {\n" +
                "\t\t\"index\": \"abc1\"\n" +
                "\t},\n" +
                "\t\"context\": [{\n" +
                "\n" +
                "\t\t\"index\": \"123123\"\n" +
                "\t}, {\n" +
                "\t\t\"index\": \"456456\"\n" +
                "\t}]\n" +
                "}]";
        new JacksonRead().readVal(data);
        new JacksonRead().readValList(dataList);
        new JacksonRead().readValArray(dataList);
    }

    /**
     * 处理Bean类型
     */
    public void readVal(String data) {
        /**
         * {
         * 	"msg": "success",
         * 	"code": 0,
         * 	"table": {
         * 		"index": "abc"
         *        },
         * 	"context": [{
         * 		"index": "123"
         *    }, {
         * 		"index": "456"
         *    }],
         *    "inner": {"username":"inner"}
         * }
         */
        DemoBean readValue = null;
        try {
            readValue = objectMapper.readValue(data, DemoBean.class);
            System.out.println(objectMapper.writeValueAsString(readValue));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理数组类型类型
     */
    public void readValList(String data) {
        /**
         * [{
         * 	"msg": "success",
         * 	"code": 0,
         * 	"table": {
         * 		"index": "abc"
         *        },
         * 	"context": [{
         *
         * 		"index": "123"
         *    }, {
         * 		"index": "456"
         *    }]
         * }, {
         * 	"msg": "error",
         * 	"code": 0,
         * 	"table": {
         * 		"index": "abc1"
         *    },
         * 	"context": [{
         *
         * 		"index": "123123"
         *    }, {
         * 		"index": "456456"
         *    }]
         * }]
         */
        List<DemoBean> readValue = null;
        try {
            readValue = objectMapper.readValue(data, new TypeReference<List<DemoBean>>() {
            });

            // [{"msg":"success","code":0,"table":{"index":"abc"},"context":[{"index":"123"},{"index":"456"}]},
            // {"msg":"error","code":0,"table":{"index":"abc1"},"context":[{"index":"123123"},{"index":"456456"}]}]
            System.out.println(objectMapper.writeValueAsString(readValue));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readValArray(String data) {
        DemoBean[] readValue = new DemoBean[0];
        try {
            readValue = objectMapper.readValue(data, new TypeReference<DemoBean[]>() {
            });

            // [{"msg":"success","code":0,"table":{"index":"abc"},"context":[{"index":"123"},{"index":"456"}]},
            // {"msg":"error","code":0,"table":{"index":"abc1"},"context":[{"index":"123123"},{"index":"456456"}]}]
            System.out.println(objectMapper.writeValueAsString(readValue));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}