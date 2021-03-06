/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.spring.service.impl;

import com.myz.spring.service.BaseService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author maoyz on 2018/8/29
 * @version: v1.0
 */
@Service("secondService")
@Primary
public class SecondServiceImpl implements BaseService {

    @Override
    public void execute() {
        System.out.println("SecondService execute()");
    }
}
