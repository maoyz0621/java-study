/**
 * Copyright 2019 Inc.
 **/
package com.myz.design.adapter;

/**
 * 适配 HttpController
 * @author maoyz0621 on 19-10-24
 * @version: v1.0
 */
public class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }

    @Override
    public void handle(Object handler) {
        ((HttpController) handler).doRequest();
    }
}
