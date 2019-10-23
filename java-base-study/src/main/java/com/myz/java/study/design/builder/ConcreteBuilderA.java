/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.builder;

/**
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public class ConcreteBuilderA extends Builder {

    @Override
    public void buildCpu() {
        computer.add("ConcreteBuilderA cpu");
    }

    @Override
    public void buildMainboard() {
        computer.add("ConcreteBuilderA mainBoard");
    }

    @Override
    public void buildHD() {
        computer.add("ConcreteBuilderA hd");
    }

}
