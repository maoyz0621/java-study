/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda.consumer;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Consumer
 * @author maoyz0621 on 19-8-2
 * @version: v1.0
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Map<String, Object> map = Maps.newHashMap();
        Consumer<Map<String, Object>> consumer = param -> {
            param.put("key1", "value1");
        };

        consumer.accept(map);
        System.out.println(map);

        Consumer<Map<String, Object>> other = param -> {
            param.put("key2", "value2");
        };

        consumer.andThen(other).accept(map);

        System.out.println(map);
    }
}
