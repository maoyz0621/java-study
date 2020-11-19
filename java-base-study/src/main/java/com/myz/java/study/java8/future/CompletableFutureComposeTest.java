package com.myz.java.study.java8.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class CompletableFutureComposeTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    final ExecutorService executor = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @After
    public void end() {
        while (true) {
        }
    }

    /////////////////////////////////////////// thenCompose 存在着先后顺序 /////////////////////////////////////////////////

    /**
     * thenCompose
     */
    @Test
    public void testCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        System.out.println(future.get());
    }

    /**
     * thenCompose可以用于组合多个CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序
     */
    @Test
    public void test() throws Exception {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "101")
                .thenComposeAsync(s -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thenCompose 1");
                    return CompletableFuture.supplyAsync(() -> s + "100");
                }, executor)
                .thenCompose(s -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thenCompose 2");
                    return CompletableFuture.supplyAsync(() -> Double.parseDouble(s));
                });

        // 101100.0
        System.out.println(future.get());
    }
}
