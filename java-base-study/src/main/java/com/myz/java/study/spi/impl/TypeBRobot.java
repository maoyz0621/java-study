/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-11 15:20  Inc. All rights reserved.
 */
package com.myz.java.study.spi.impl;

import com.myz.java.study.spi.Robot;

/**
 * @author maoyz
 */
public class TypeBRobot implements Robot {
    @Override
    public void sayHello() {
        System.out.println("TypeBRobot");
    }
}