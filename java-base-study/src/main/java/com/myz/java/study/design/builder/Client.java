/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.builder;

/**
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public class Client {

    public static void main(String[] args) {
        // 建造者
        Builder builder = new ConcreteBuilderA();
        // 指挥者
        Director director = new Director(builder);
        // 指挥者指挥组装过程
        director.construct();

        builder.getComputer().show();
    }

}
