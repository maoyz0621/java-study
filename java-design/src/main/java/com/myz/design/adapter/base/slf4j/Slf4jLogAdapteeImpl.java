/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base.slf4j;

/**
 * @author maoyz0621 on 2023/7/12
 * @version v1.0
 */
public class Slf4jLogAdapteeImpl implements Slf4jLogAdaptee {
    @Override
    public void debug(String s) {
        System.out.println("Slf4jLog:" + s);
    }
}