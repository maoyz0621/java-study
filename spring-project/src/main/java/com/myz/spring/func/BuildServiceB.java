/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.func;

import com.myz.spring.func.param.ParamA;
import com.myz.spring.func.param.ParamB;
import com.myz.spring.func.param.ResultC;
import org.springframework.stereotype.Component;

/**
 * @author maoyz0621 on 2022/10/15
 * @version v1.0
 */
@Component
public class BuildServiceB implements BuildService {

    @Override
    public String type() {
        return "b";
    }

    @Override
    public ResultC build(ParamA t, ParamB u) {
        System.out.println("BuildServiceB build");
        return null;
    }

    @Override
    public ResultC build(ParamA t) {
        return null;
    }
}