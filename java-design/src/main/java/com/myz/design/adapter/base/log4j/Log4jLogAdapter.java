/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base.log4j;

import com.myz.design.adapter.base.LogTarget;

/**
 * @author maoyz0621 on 2023/7/11
 * @version v1.0
 */
public class Log4jLogAdapter implements LogTarget {

    private Log4jLogAdaptee log4jLogAdaptee;

    public Log4jLogAdapter(String clazz) {
        this.log4jLogAdaptee = new Log4jLogAdaptee();
    }

    // 依赖Adaptee类
    public Log4jLogAdapter(Log4jLogAdaptee log4jLogAdaptee) {
        this.log4jLogAdaptee = log4jLogAdaptee;
    }

    @Override
    public boolean isDebugEnable() {
        return true;
    }

    @Override
    public void debug(String s) {
        log4jLogAdaptee.debug(s);
    }
}