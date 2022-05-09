package com.myz.java.study.java8.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author maoyz
 * @version V1.0
 * @date 2022/5/9 16:05
 */
public class CompletableFutureAllOfTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    final ExecutorService executor = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void test() throws Exception {
        List<CompletableFuture<Boolean>> completableFutureList = new ArrayList<>();

        completableFutureList.add(getBooleanCompletableFuture(1000L, true));
        completableFutureList.add(getBooleanCompletableFuture(1500L, false));
        completableFutureList.add(getBooleanCompletableFuture(2000L, true));

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Boolean>> listCompletableFuture = voidCompletableFuture.thenApply(v -> completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        // 获取结果值
        System.out.println(Thread.currentThread().getName() + " - start " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        List<Boolean> booleans = listCompletableFuture.get();
        System.out.println(Thread.currentThread().getName() + " - end " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(booleans);
    }

    private CompletableFuture<Boolean> getBooleanCompletableFuture(long millis, boolean result) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - start " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " - end " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return result;
        }, executor);
    }
}
