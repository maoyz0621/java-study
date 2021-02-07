package com.myz.java.study.java8.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class CompletableFutureApplyTest {

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

    /////////////////////////////////////////// thenApply  /////////////////////////////////////////////////

    /**
     * thenApply
     */
    @Test
    public void testApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> join = CompletableFuture.supplyAsync(() -> LocalDateTime.now().format(formatter))
                .thenApply((s) -> Thread.currentThread().getName() + " => now is " + s)
                .thenApplyAsync(s -> s + " = " + Thread.currentThread().getName() + " Hello World");

        System.out.println(join.get());
    }

    /**
     * thenApplyAsync
     */
    @Test
    public void testApply0() throws ExecutionException, InterruptedException {
        CompletableFuture<String> join = CompletableFuture.supplyAsync(() -> LocalDateTime.now().format(formatter))
                .thenApplyAsync((s) -> Thread.currentThread().getName() + " => now is " + s)
                .thenApplyAsync(s -> s + " = " + Thread.currentThread().getName() + " Hello World", executor);

        System.out.println(join.get());
    }

    /**
     * String -> Integer -> Double
     */
    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "10")
                .thenApply(Integer::parseInt)
                .thenApply((i) -> i * 1.0D);
        System.out.println(future.get());
    }
}
