/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base;

/**
 * @author maoyz0621 on 2023/7/12
 * @version v1.0
 */
public class LogTargetTest {

    public static void main(String[] args) {
        LogTarget log = LogTargetFactory.getLog(LogTargetTest.class);
        log.debug("hahah");
    }
}