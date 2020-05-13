package com.myz.java.study.spi.impl;

import com.myz.java.study.spi.Robot;

/**
 * @author maoyz
 */
public class TypeARobot implements Robot {
    @Override
    public void sayHello() {
        System.out.println("TypeARobot");
    }

    @Override
    public String m1() {
        return "TypeARobot";
    }
}