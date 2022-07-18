/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.java8.lambda.test;

import java.util.Map;

/**
 * @author maoyz0621 on 2022/6/21
 * @version v1.0
 */
public class TestService {

    public String mo(Map<String, String> param) {
        return param.get("a");
    }

    public String mo0() {
        return "111";
    }

    public void mo1(Map<String, String> param) {
        String s = param.get("a");
    }
}