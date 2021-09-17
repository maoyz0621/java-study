/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-11 15:22  Inc. All rights reserved.
 */
package com.myz.opensource.google.spi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maoyz
 */
public class SpiClient {

    private static final Map<String, Robot> ROBOT_MAP = new ConcurrentHashMap<>();
    private static final List<Robot> ROBOT_PROVIDERS = new ArrayList<>();

    static {
        ServiceLoader.load(Robot.class).forEach(ROBOT_PROVIDERS::add);
        // 排序
        Collections.sort(ROBOT_PROVIDERS);
    }

    public static Robot getRobot(String type) {
        return ROBOT_MAP.computeIfAbsent(type, SpiClient::findRobot);
    }

    private static Robot findRobot(String type) {
        for (Robot provider : ROBOT_PROVIDERS) {
            if (provider.support(type)) {
                return provider;
            }
        }
        throw new UnsupportedOperationException("找不到对应响应提取器");
    }
}