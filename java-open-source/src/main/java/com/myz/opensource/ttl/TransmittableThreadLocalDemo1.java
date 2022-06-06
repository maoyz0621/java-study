/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 2021/7/22
 * @version v1.0
 */
public class TransmittableThreadLocalDemo1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransmittableThreadLocalDemo1.class);

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> context = new TransmittableThreadLocal<>();
        // ThreadLocal<String> context = new InheritableThreadLocal<>();
        context.set("abc");
        System.out.println("context = " + context.get());

        System.out.println("\r\n===========================\r\n");

        new Thread(() -> {
            // 在子线程中可以读取值
            LOGGER.info("子线程id={}, 子线程中获取父线程的值:{}", Thread.currentThread().getId(), context.get());
            context.set("abc-1");
            LOGGER.info("子线程id={}, 子线程修改变量值后子线程的值context = {}", Thread.currentThread().getId(), context.get());
        }).start();

        Thread.sleep(2000);
        System.out.println("\n============================\r\n");
        LOGGER.info("子线程修改变量值后main的值context = {}", context.get());
    }
}