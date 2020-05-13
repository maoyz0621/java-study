/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-11 15:22  Inc. All rights reserved.
 */
package com.myz.java.study.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author maoyz
 */
public class SpiClient {

    public static void main(String[] args) {
        ServiceLoader<Robot> robots = ServiceLoader.load(Robot.class);

        robots.forEach(Robot::sayHello);

        Iterator<Robot> iterator = robots.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}