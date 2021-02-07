package com.myz.java.study.java8.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class CompletableFutureStaticTest {

    final ExecutorService executor = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @After
    public void end() {
        while (true) {
        }
    }

    /////////////////////////////////////////// runAsync 无返回值 /////////////////////////////////////////////////

    /**
     * runAsync
     */
    @Test
    public void test0() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        // null
        System.out.println("========== " + future.get());

        for (int i = 0; i < 50; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

    /**
     * runAsync(), 使用自定义线程池
     */
    @Test
    public void test1() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executor);
        // null
        System.out.println("========== " + future.get());

        for (int i = 0; i < 50; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }, executor);
        }
    }


    /////////////////////////////////////////// supplyAsync 有返回值 /////////////////////////////////////////////////

    /**
     * supplyAsync 有返回值
     */
    @Test
    public void test2() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + " - Hello";
        });
        // null
        System.out.println("========== " + future.get());

        for (int i = 0; i < 50; i++) {
            CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName() + " - Hello";
            });
            System.out.println(async.get());
        }
    }

    /**
     * supplyAsync 使用自定义线程池
     */
    @Test
    public void test3() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + " - Hello";
        }, executor);
        // null
        System.out.println("========== " + future.get());

        for (int i = 0; i < 50; i++) {
            CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName() + " - Hello";
            }, executor);
            System.out.println(async.get());
        }
    }
}
