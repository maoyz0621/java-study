/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.design.adapter;

/**
 * 适配 AnnotationController
 * @author maoyz0621 on 19-10-24
 * @version: v1.0
 */
public class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof AnnotationController);
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController) handler).doRequest();
    }
}
