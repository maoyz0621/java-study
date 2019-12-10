/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.jvm;

import org.junit.Test;

/**
 * VirtualMachineError
 *
 * @author maoyz0621 on 19-11-24
 * @version: v1.0
 */
public class JvmError {

    /**
     * java.lang.StackOverflowError
     * 运行参数
     * -Xms5m
     * -Xmx5m
     */
    @Test
    public void testStackOverflowError() {
        a();
    }

    private void a() {
        a();
    }

    /**
     * OutOfMemoryError
     * 1. Java heap space
     * 运行参数
     * -Xms5m
     * -Xmx5m
     */
    @Test
    public void testOutOfMemoryError1() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * OutOfMemoryError
     * 2. GC overhead limit exceeded
     * 运行参数
     * -Xms5m
     * -Xmx5m
     */
    @Test
    public void testOutOfMemoryError2() {
    }
}
