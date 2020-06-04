/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.delegate;

/**
 * @author maoyz on 18-10-19
 * @version: v1.0
 */
public interface ITarget {

    //普通员工执行任务
    //在公司中，员工给执行任务
    //规定在一周之内必须完成
    void doing(String command);
}
