package com.myz.opensource.google.spi.impl;

import com.google.auto.service.AutoService;
import com.myz.opensource.google.spi.Robot;


/**
 * @author maoyz
 */
@AutoService(Robot.class)
public class TypeDRobot implements Robot {
    @Override
    public void sayHello() {
        System.out.println("TypeDRobot");
    }
}