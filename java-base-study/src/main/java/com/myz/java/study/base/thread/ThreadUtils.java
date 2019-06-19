/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.thread;

/**
 * 线程工具类
 *
 * @author maoyz0621 on 19-3-6
 * @version: v1.0
 */
public class ThreadUtils extends org.apache.commons.lang3.ThreadUtils {

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
