package com.myz.java.study.java8.future;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CompletableFutureAllOfTest.class);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
    final ExecutorService executor = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void test() throws Exception {
        List<CompletableFuture<Boolean>> completableFutureList = Lists.newArrayListWithExpectedSize(3);

        completableFutureList.add(getBooleanCompletableFuture(3000L, true));
        completableFutureList.add(getBooleanCompletableFuture(3500L, false));
        completableFutureList.add(getBooleanCompletableFuture(4000L, true));

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Boolean>> listCompletableFuture = voidCompletableFuture.thenApply(v -> completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        // 获取结果值
        log.info("============= join " + Thread.currentThread().getName() + " - start " + LocalDateTime.now().format(formatter));
        List<Boolean> booleans = listCompletableFuture.get(5000, TimeUnit.MILLISECONDS);
        log.info("============= get" + Thread.currentThread().getName() + " - end " + LocalDateTime.now().format(formatter));
        log.info("result={}", booleans);
    }

    private CompletableFuture<Boolean> getBooleanCompletableFuture(long millis, boolean result) {
        return CompletableFuture.supplyAsync(() -> {
            log.info(Thread.currentThread().getName() + " - start " + LocalDateTime.now().format(formatter));
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
            }
            log.info(Thread.currentThread().getName() + " - end " + LocalDateTime.now().format(formatter));
            return result;
        }, executor).exceptionally((e) -> {
            // 异常处理
            log.error(Thread.currentThread().getName() + " error");
            return false;
        });
    }
}
