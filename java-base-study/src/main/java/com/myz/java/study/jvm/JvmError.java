/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.jvm;

import org.junit.Test;

/**
 * VirtualMachineError
 *
 * @author maoyz0621 on 19-11-24
 * @version v1.0
 */
public class JvmError {

    /**
     * java.lang.StackOverflowError
     * 运行参数 -Xms5m -Xmx5m -XX:+PrintGCDetails
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
     * 运行参数 -Xms5m -Xmx5m -XX:+PrintGCDetails  -XX:+PrintCommandLineFlags -XX:+UseSerialGC
     * [GC (Allocation Failure) [DefNew: 1664K->192K(1856K), 0.0009650 secs] 1664K->717K(5952K), 0.0009926 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [DefNew: 1635K->146K(1856K), 0.0004804 secs][Tenured: 1299K->1429K(4096K), 0.0018814 secs] 2904K->1429K(5952K), [Metaspace: 3268K->3268K(4480K)], 0.0023918 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [Tenured: 1429K->1182K(4096K), 0.0017740 secs] 1429K->1182K(5952K), [Metaspace: 3268K->3268K(4480K)], 0.0017915 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     */
    @Test
    public void testOutOfMemoryErrorSerialGC() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * OutOfMemoryError
     * 1. Java heap space
     * 运行参数 -Xms5m -Xmx5m -XX:+PrintGCDetails  -XX:+UseParallelGC -XX:+UseParallelOldGC
     * [GC (Allocation Failure) [PSYoungGen: 1536K->248K(1792K)] 1536K->796K(5888K), 0.0007106 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 208K->0K(1536K)] [ParOldGen: 1684K->1223K(4096K)] 1892K->1223K(5632K), [Metaspace: 3269K->3269K(4480K)], 0.0048404 secs] [Times: user=0.16 sys=0.00, real=0.02 secs]
     * [GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] 1223K->1223K(5632K), 0.0001607 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 1223K->1162K(4096K)] 1223K->1162K(5632K), [Metaspace: 3269K->3267K(4480K)], 0.0058044 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     */
    @Test
    public void testOutOfMemoryErrorParallelGC() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * OutOfMemoryError
     * 1. Java heap space
     * 运行参数 -Xms5m -Xmx5m -XX:+PrintGCDetails  -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * [GC (Allocation Failure) [ParNew: 1216K->128K(1216K), 0.0004432 secs] 1733K->888K(6016K), 0.0004714 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [ParNew: 599K->57K(1216K), 0.0003498 secs][CMS: 1465K->1163K(4800K), 0.0024996 secs] 2027K->1163K(6016K), [Metaspace: 3269K->3269K(4480K)], 0.0028816 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [CMS: 1163K->1115K(4800K), 0.0020632 secs] 1163K->1115K(6016K), [Metaspace: 3269K->3269K(4480K)], 0.0020863 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     */
    @Test
    public void testOutOfMemoryErrorCMSGC() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * OutOfMemoryError
     * 1. Java heap space
     * 运行参数 -Xms5m -Xmx5m -XX:+PrintGCDetails  -XX:+UseG1GC
     */
    @Test
    public void testOutOfMemoryErrorG1GC() {
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
