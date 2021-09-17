package com.myz.opensource.google.spi;

import lombok.Synchronized;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/17 16:12
 */
public class RobotLoader {
    private static final Map<String, Robot> ROBOT_MAP = new ConcurrentHashMap<>();

    public static Robot getRobot(String type) {
        return ROBOT_MAP.computeIfAbsent(type, RobotLoader::findRobot);
    }

    @Synchronized
    private static Robot findRobot(String type) {
        ServiceLoader<Robot> loader = ServiceLoader.load(Robot.class);
        for (Robot provider : loader) {
            if (provider.support(type)) {
                return provider;
            }
        }
        throw new UnsupportedOperationException("找不到对应响应提取器");
    }

}
