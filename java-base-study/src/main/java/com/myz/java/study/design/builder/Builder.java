/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.builder;

/**
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public abstract class Builder {

    protected Computer computer = new Computer();

    //第一步：装CPU
    //声 明为抽象方法，具体由子类实现
    protected abstract void buildCpu();

    //第二步：装主板
    //声明为抽象方法，具体由子类实现
    protected abstract void buildMainboard();

    //第三步：装硬盘
    //声明为抽象方法，具体由子类实现
    protected abstract void buildHD();

    //返回产品的方法：获得组装好的电脑
    public Computer getComputer() {
        return computer;
    }

}
