/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.design.adapter;

/**
 * 适配者角色（Adapter）: 通过包装被适配的对象，将原接口转换为用户所期待的目标接口
 *
 * @author maoyz0621 on 19-10-23
 * @version: v1.0
 */
public interface HandlerAdapter {

    boolean supports(Object handler);

    void handle(Object handler);

}
