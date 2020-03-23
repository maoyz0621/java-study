package com.myz.java.study.java8.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class CompletableFuturethenAcceptBothTest {

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

    /////////////////////////////////////////// thenAcceptBoth  BiConsumer /////////////////////////////////////////////////

    @Test
    public void test() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("thenCombine 1");
            } catch (InterruptedException e) {
            }
            return "101";
        }).thenAcceptBoth(
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
                    System.out.println(Double.parseDouble(s + i));
                });

        // null
        System.out.println(future.get());
    }
}
