/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.controller;

import com.myz.cache.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Api(value = "事务的失效行为", tags = "1.0")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "所有用户信息", notes = "用户")
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name = "id", value = "必传字段", paramType = "path", required = true, dataType = "Integer"),
    //         @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String", paramType = "query")
    // })
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        return userService.findAll().toString();
    }

    @ApiOperation(value = "保存用户", notes = "保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save() {
        userService.save();
        return "Success";
    }

    @ApiOperation(value = "本类方法调用", notes = "调用")
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public String execute() {
        userService.execute();
        return "execute";
    }

    @ApiOperation(value = "AopContext调用", notes = "AopContext")
    @RequestMapping(value = "/executeWithAopContext", method = RequestMethod.GET)
    public String executeWithAopContext() {
        userService.executeWithAopContext();
        return "executeWithAopContext";
    }
}
