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

    private static ServiceLoader<Robot> robots = ServiceLoader.load(Robot.class);

    public static void main(String[] args) {
        robots.forEach(Robot::sayHello);

        Iterator<Robot> iterator = robots.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}