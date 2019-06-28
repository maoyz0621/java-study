/**
 * Copyright 2019 Inc.
 **/
package com.myz.ajax.web;

import com.myz.ajax.common.Result;
import com.myz.ajax.common.ResultGenerator;
import com.myz.ajax.model.Address;
import com.myz.ajax.model.UserJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

/**
 * 处理JSON化
 *
 * @author maoyz0621 on 19-6-22
 * @version: v1.0
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    private static final Logger logger = LoggerFactory.getLogger(JsonController.class);

    /**
     *
     * @param userJson
     * @return
     */
    @PostMapping(value = "/a")
    public Result param(@Valid UserJson userJson) {
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }

    /**
     * 入参:
     * {
     *     "birthday": "2019-06-28 21:40:43",
     *     "postCode": "12323213",
     *     "phone": "1233333333",
     *     "age": 12,
     *     "address": {
     *         "province": "aaa",
     *         "city": "aaaa"
     *     },
     *     "user_name": "aaa",
     *     "password": "111111"
     * }
     * 出参:
     * {
     *     "age": 12,
     *     "phone": "1233333333",
     *     "birthday": "2019-06-28 21:40:43",
     *     "postCode": "12323213",
     *     "address": {
     *         "province": "aaa",
     *         "city": "aaaa",
     *         "towns": []
     *     },
     *     "user_name": "aaa",
     *     "mounts": [],
     *     "amount": []
     * }
     */
    @PostMapping(value = "/b", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Result json(@RequestBody @Validated UserJson userJson) {
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }

    /**
     * 其中 "phone"属性忽略, username -> "user_name"
     *
     * {
     *     "code": "SUCCESS",
     *     "message": "success",
     *     "data": {
     *         "age": 1,
     *         "price": 0,
     *         "birthday": "2019-06-27 23:35:28",
     *         "postCode": "",
     *         "address": {
     *             "province": "jiangShu",
     *             "city": "nanjing",
     *             "towns": []
     *         },
     *         "user_name": "aaaaa",
     *         "mounts": [],
     *         "amount": []
     *     }
     * }
     */
    @GetMapping(value = "/c")
    public Result get() {
        UserJson userJson = new UserJson();
        userJson.setUsername("aaaaa");
        userJson.setAge(1);
        userJson.setPhone("23243243243");
        userJson.setBirthday(new Date());
        userJson.setPassword("@@@@@@@@@@@@@@@");
        userJson.setAddress(new Address("jiangShu", "nanjing", new ArrayList<>()));
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }

    /**
     * {
     *     "code": "SUCCESS",
     *     "message": "success",
     *     "data": {
     *         "age": 0,
     *         "price": 0,
     *         "birthday": "2019-06-27 23:34:59",
     *         "postCode": "",
     *         "address": {
     *             "province": "",
     *             "city": "",
     *             "towns": []
     *         },
     *         "user_name": "",
     *         "mounts": [],
     *         "amount": []
     *     }
     * }
     */
    @GetMapping(value = "/d")
    public Result getNull() {
        UserJson userJson = new UserJson();
        userJson.setPhone("23243243243");
        userJson.setBirthday(new Date());
        userJson.setPassword("@@@@@@@@@@@@@@@");
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }
}
