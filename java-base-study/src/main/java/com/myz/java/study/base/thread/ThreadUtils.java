/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.thread;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程工具类
 *
 * @author maoyz0621 on 19-3-6
 * @version: v1.0
 */
public class ThreadUtils extends org.apache.commons.lang3.ThreadUtils {

    protected static ThreadPoolExecutor globalThreadPoolExecutor;

    // 用于判断是否关闭线程池
    public static volatile boolean isClose = false;


    public static void shutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            isClose = true;
            globalThreadPoolExecutor.shutdown();
        }));
    }
    /**
     * 可用处理器数
     */
    public static int getRunThreads() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 监控当前JVM空闲内存
     */
    public static long monitorFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 监控当前JVM空闲内存
     */
    public static long monitorTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }
}
