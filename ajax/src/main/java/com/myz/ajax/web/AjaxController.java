/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.ajax.web;

import com.alibaba.fastjson.JSON;
import com.myz.ajax.common.Result;
import com.myz.ajax.common.ResultGenerator;
import com.myz.ajax.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 使用@RequestBody.当请求content_type为：application/json类型的请求，数据类型为json时， json格式如下：{"aaa":"111","bbb":"222"},请求参数放在body中
 * 不使用@RequestBody.当请求content_type为：application/x-www-form-urlencoded类型的或multipart/form-data时，数据格式为aaa=111&bbb=222。
 *
 * @author maoyz on 2018/11/14
 * @version: v1.0
 */
@RequestMapping("/ajax")
@Controller
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

    @GetMapping("/index.html")
    public String index() {
        return "ajax";
    }

    //////////////////////////////// GET FORM///////////////////////////////////

    /**
     * get请求，以HttpServletRequest接收
     * request接收的参数都是String
     */
    @GetMapping("/get")
    @ResponseBody
    public Result<User> ajaxGet(HttpServletRequest request) {
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        logger.info("************* {} : {} ***************", username, age);
        return ResultGenerator.genSuccessResult().setData(new User(username, Integer.valueOf(age)));
    }

    /**
     * get请求,封装map
     */
    @GetMapping("/getMap")
    @ResponseBody
    public Result<User> ajaxGet(@RequestParam Map param) {
        logger.info("************* {} : {}***************", param.get("username"), param.get("age"));
        return ResultGenerator.genSuccessResult().setData(
                new User((String) (param.get("username")),
                        Integer.valueOf((String) param.get("age")))
        );
    }

    /**
     * get请求,封装model
     */
    @GetMapping("/getModel")
    @ResponseBody
    public Result<User> ajaxGet(User user) {
        logger.info("************* {} ***************", user);
        return ResultGenerator.genSuccessResult().setData(user);
    }

    //////////////////////////////// GET JSON///////////////////////////////////

    /**
     * Get请求 ,发送数据类型JSON.stringify()
     */
    @GetMapping("/getJsonParam")
    @ResponseBody
    public Result ajaxGetJson(@RequestBody String param, HttpServletRequest request) {
        System.out.println(param);
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        return ResultGenerator.genSuccessResult().setData(new User(username, Integer.valueOf(age)));
    }


    /**
     * Get请求 ,发送数据类型JSON.stringify()
     */
    @GetMapping("/getJsonModel")
    @ResponseBody
    public Result ajaxGetJson(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user);
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        return ResultGenerator.genSuccessResult().setData(new User(username, Integer.valueOf(age)));
    }

    //////////////////////////////// POST FORM///////////////////////////////////

    /**
     * 请求形式：
     * username: maoyz
     * age: 12
     */
    @PostMapping("/postRequest")
    @ResponseBody
    public Result ajaxPost(HttpServletRequest request) {
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        logger.info("************* {}; {} ***************", username, age);
        return ResultGenerator.genSuccessResult().setData(new User(username, Integer.valueOf(age)));
    }

    @PostMapping("/postModel")
    @ResponseBody
    public Result ajaxPost(User user) {
        logger.info("************* {} ***************", user);
        System.out.println(user);
        return ResultGenerator.genSuccessResult().setData(user);
    }

    @PostMapping("/postMap")
    @ResponseBody
    public Result ajaxPost(@RequestParam Map param) {
        logger.info("************* {} : {}***************", param.get("username"), param.get("age"));
        return ResultGenerator.genSuccessResult().setData(
                new User(String.valueOf(param.get("username")),
                        Integer.valueOf((String) param.get("age")))
        );
    }

    //////////////////////////////// POST JSON ///////////////////////////////////

    /**
     * 请求形式：
     * {"username":"maoyz","age":12}
     * 以String接收
     */
    @PostMapping("/postJsonString")
    @ResponseBody
    public Result ajaxPostJson(@RequestBody String param, HttpServletRequest request) {
        logger.info("************* {} ***************", param);
        // json转map或model
        User user = JSON.parseObject(param, User.class);
        Map map = JSON.parseObject(param, Map.class);
        logger.info("************* age instanceof Integer: {} ***************", map.get("age") instanceof Integer);
        return ResultGenerator.genSuccessResult().setData(user);
    }

    /**
     * 请求形式：
     * {"username":"maoyz","age":12}
     * 以实体类接收
     */
    @PostMapping("/postJsonModel")
    @ResponseBody
    public Result ajaxPostJson(@RequestBody User user, HttpServletRequest request) {
        logger.info("************* {} ***************", user);
        return ResultGenerator.genSuccessResult().setData(user);
    }

    /**
     * 请求形式：
     * {"username":"maoyz","age":12}
     * 以Map类接收
     */
    @PostMapping("/postJsonMap")
    @ResponseBody
    public Result ajaxPostJson(@RequestBody Map map) {
        logger.info("************* {} ***************", map);
        if (map != null && !map.isEmpty()) {
            logger.info("************* age instanceof Integer: {} ***************", map.get("age") instanceof Integer);
        }
        return ResultGenerator.genSuccessResult().setData(new User());
    }
}
