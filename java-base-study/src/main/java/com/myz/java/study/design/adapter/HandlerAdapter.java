/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.design.adapter;

/**
 * @author maoyz0621 on 19-10-23
 * @version: v1.0
 */
public interface HandlerAdapter {

    boolean supports(Object handler);

    void handle(Object handler);

}
