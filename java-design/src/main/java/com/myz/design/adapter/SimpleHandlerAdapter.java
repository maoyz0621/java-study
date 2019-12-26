/**
 * Copyright 2019 Inc.
 **/
package com.myz.design.adapter;

/**
 * 适配 SimpleController
 * @author maoyz0621 on 19-10-24
 * @version: v1.0
 */
public class SimpleHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController) handler).doRequest();
    }
}
