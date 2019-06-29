/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.spring.service.impl;

import com.myz.spring.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author maoyz on 2018/8/29
 * @version: v1.0
 */
@Service("firstService")
public class FirstServiceImpl implements BaseService {

    @Override
    public void execute() {
        System.out.println("FirstService execute()");
    }
}
