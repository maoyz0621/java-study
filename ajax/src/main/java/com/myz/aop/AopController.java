package com.myz.aop;

import com.myz.ajax.common.Result;
import com.myz.ajax.common.ResultGenerator;
import com.myz.ajax.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/30 17:07
 */
@RestController
public class AopController {

    @Resource
    IAopService aopService;

    @GetMapping("/aop")
    public Result<User> ajaxGet(HttpServletRequest request) {
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        aopService.say();
        return ResultGenerator.genSuccessResult().setData(new User(username, Integer.valueOf(age)));
    }
}
