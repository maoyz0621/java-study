package com.myz.java.study.juc.thread.threadpool;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/8/25 9:18
 */
public class ThreadPoolTaskExecutorTest {

    @Test
    public void test() throws InterruptedException {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        for (int i = 0; i < 50; i++) {
            threadPoolTaskExecutor.setThreadNamePrefix("abc_");
            threadPoolTaskExecutor.initialize();
            threadPoolTaskExecutor.execute(() -> {
                System.out.println("name = " + Thread.currentThread());
            });

            threadPoolTaskExecutor.setThreadNamePrefix("asd");
            threadPoolTaskExecutor.initialize();
            threadPoolTaskExecutor.execute(() -> {
                System.out.println("name1 = " + Thread.currentThread());
            });
        }

        Thread.sleep(10000);
    }
}
