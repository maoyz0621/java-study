/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.delegate;

/**
 * 受托人B
 *
 * @author maoyz on 18-10-19
 * @version: v1.0
 */
public class TargetB implements ITarget {

    @Override
    public void doing(String command) {
        System.out.println("我是员工B，我现在开始干" + command + "工作");
    }
}
