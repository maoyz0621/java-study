/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maoyz0621 on 2021/7/22
 * @version v1.0
 */
public class TransmittableThreadLocalDemo2 {

    public static ThreadLocal<String> context = new TransmittableThreadLocal<>();
    public static ExecutorService executorService =
            TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

    public static void main(String[] args) throws InterruptedException {
        context.set("abc");
        System.out.println("context = " + context.get());

        executorService.submit(() -> {
            System.out.println("第一次子线程读取：" + context.get());
        });
        Thread.sleep(1000);

        context.set("abc-1");
        System.out.println("context-1 = " + context.get());

        // 父线程修改了本地变量后，新提交到线程池的子线程成功读取到新值。
        executorService.submit(() -> {
            System.out.println("第二次子线程读取：" + context.get());

            // 子线程修改变量值
            context.set("abc-2");
            System.out.println("第三次子线程读取：" + context.get());
        });

        Thread.sleep(1000);

        // 子线程修改不影响父线程的变量值
        System.out.println("context-2 = " + context.get());
        Thread.sleep(3000);
    }
}