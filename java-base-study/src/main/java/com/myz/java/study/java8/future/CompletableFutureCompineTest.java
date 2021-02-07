package com.myz.java.study.java8.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class CompletableFutureCompineTest {

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

    /////////////////////////////////////////// thenCombine(BiFunction)  多个CompletableFuture之间是并行执行的 ,返回Void /////////////////////////////////////////////////

    @Test
    public void test() throws Exception {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("thenCombine 1");
            } catch (InterruptedException e) {
            }
            return "101";
        }).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                        System.out.println("thenCombine 2");
                    } catch (InterruptedException e) {
                    }
                    return "100";
                }), (s, i) -> {
                    System.out.println("s = " + s);
                    System.out.println("i = " + i);
                    return Double.parseDouble(s + i);
                });

        // null
        System.out.println(future.get());
    }
}
