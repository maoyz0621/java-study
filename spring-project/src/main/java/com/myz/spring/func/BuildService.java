/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.func;

import com.myz.spring.func.param.ParamA;
import com.myz.spring.func.param.ParamB;
import com.myz.spring.func.param.ResultC;

/**
 * @author maoyz0621 on 2022/10/14
 * @version v1.0
 */
public interface BuildService<T extends ParamA, U extends ParamB, R extends ResultC> {

    String type();

    R build(T t, U u);

    R build(T t);

}