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

    @PostMapping(value = "/a")
    public Result param(@Valid UserJson userJson) {
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }

    @PostMapping(value = "/b", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Result json(@RequestBody @Validated UserJson userJson) {
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }

    /**
     * "data":{"age":1,"birthday":"2019-06-23 14:48:05","address":{"province":"jiangShu","city":"nanjing"},"user_name":"aaaaa"}}
     * 其中 "phone"属性忽略, username -> "user_name"
     *
     * @return
     */
    @GetMapping(value = "/c")
    public Result get() {
        UserJson userJson = new UserJson();
        userJson.setUsername("aaaaa");
        userJson.setAge(1);
        userJson.setPhone("23243243243");
        userJson.setBirthday(new Date());
        userJson.setPassword("@@@@@@@@@@@@@@@");
        userJson.setAddress(new Address("jiangShu", "nanjing"));
        logger.info("**************** {} ******************", userJson);
        return ResultGenerator.genSuccessResult(userJson);
    }
}
