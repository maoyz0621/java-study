/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base;

import java.lang.reflect.Constructor;

/**
 * @author maoyz0621 on 2023/7/12
 * @version v1.0
 */
public class LogTargetFactory {

    private static Constructor<? extends LogTarget> logConstructor;

    static {
        tryImplementation(LogTargetFactory::useSlf4jLog);
        tryImplementation(LogTargetFactory::useLog4jLog);
    }

    public static LogTarget getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public static LogTarget getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Throwable t) {
            throw new LogException("Error create log:" + t, t);
        }
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Throwable t) {

            }
        }
    }

    private static synchronized void useLog4jLog() {
        setImplementation(com.myz.design.adapter.base.log4j.Log4jLogAdapter.class);
    }


    private static synchronized void useSlf4jLog() {
        setImplementation(com.myz.design.adapter.base.slf4j.Slf4jLogAdapter.class);
    }

    private static void setImplementation(Class<? extends LogTarget> implClass) {
        try {
            // 候选者
            Constructor<? extends LogTarget> candidate = implClass.getConstructor(String.class);
            LogTarget logTarget = candidate.newInstance(LogTargetFactory.class.getName());
            if (logTarget.isDebugEnable()){
                // todo
                System.out.println("**************** debug ********************");
            }
            // 设置当前log实现类
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new LogException("Error setImplementation log:" + t, t);
        }
    }

}