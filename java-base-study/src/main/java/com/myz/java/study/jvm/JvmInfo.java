/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.jvm;

/**
 * @author maoyz0621 on 20-5-28
 * @version v1.0
 */
public class JvmInfo {

    /**
     * 配置启动参数　-Xmx2048m -Xms2048m -XX:+PrintGCDetails
     * -Xmx:1963MB
     * -Xms:1963MB
     * Heap
     * PSYoungGen total 611840K, used 41984K
     * eden space 524800K, 8% used
     * from space 87040K, 0% used
     * to   space 87040K, 0% used
     * ParOldGen  total 1398272K, used 0K
     * object space 1398272K, 0% used
     * Metaspace   used 3096K, capacity 4496K, committed 4864K, reserved 1056768K
     * class space used 339K, capacity 388K, committed 512K, reserved 1048576K
     * 参数说明：
     * 1) eden space 524800K；from space 87040K；to space 87040K
     * 三者比例：8:1:1
     * 2) PSYoungGen total 611840K;ParOldGen total 1398272K
     * 新生代：老年代 = 1:2
     * 3)
     * (611840 + 1398272) / 1024kb =
     */
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("-Xmx:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        System.out.println("-Xms:" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB");
    }
}