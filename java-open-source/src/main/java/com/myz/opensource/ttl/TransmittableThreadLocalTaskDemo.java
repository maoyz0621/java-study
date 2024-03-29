/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author maoyz0621 on 2021/7/22
 * @version v1.0
 */
public class TransmittableThreadLocalTaskDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransmittableThreadLocalTaskDemo.class);
    private ThreadLocal<String> context = new TransmittableThreadLocal<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {
        TransmittableThreadLocalTaskDemo task = new TransmittableThreadLocalTaskDemo();

        task.doTask();
    }

    public void doTask() throws Exception {
        context.set("====================== task =======================");

        // TtlRunnable.get()
        Future<?> task1 = executorService.submit(TtlRunnable.get(new TaskRunnable("task1")));
        task1.get();

        Future<?> task2 = executorService.submit(TtlRunnable.get(new TaskRunnable("task2")));
        task2.get();

        LOGGER.info("父线程context= {}", context.get());

        executorService.shutdown();


    }

    /**
     * 自定义任务类
     */
    class TaskRunnable implements Runnable {

        private String val;

        public TaskRunnable(String val) {
            this.val = val;
        }

        @Override
        public void run() {
            LOGGER.info("{} : {}", Thread.currentThread().getName(), context.get());
            context.set(val);
        }
    }
}