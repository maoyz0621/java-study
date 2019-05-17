/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.builder;

/**
 * 指挥者(Director)，用它来控制建造过程，也用来隔离用户与建造过程的关联
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 指挥装机人员组装电脑
     */
    public void construct() {
        this.builder.buildCpu();
        this.builder.buildMainboard();
        this.builder.buildHD();
    }
}
