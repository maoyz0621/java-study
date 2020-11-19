/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.spring.service.web;

import com.myz.spring.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author maoyz on 2018/8/29
 * @version: v1.0
 */
@Component
public class BaseController {

    /**
     * 一个接口有多个实现类，使用@Qualifier("")指定实现类
     */
    @Autowired
    @Qualifier("firstService")
    private BaseService baseService;

    public void execute() {
        baseService.execute();
    }
}
