/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base.log4j;

/**
 * @author maoyz0621 on 2023/7/12
 * @version v1.0
 */
public class Log4jLogAdaptee {

    public void debug(String s) {
        System.out.println("Log4jLog: " + s);
    }
}