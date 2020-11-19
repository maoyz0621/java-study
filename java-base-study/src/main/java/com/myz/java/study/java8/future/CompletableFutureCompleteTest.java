package com.myz.java.study.java8.future;

import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author maoyz
 */
public class CompletableFutureCompleteTest {

    @After
    public void end() {
        while (true) {
        }
    }

    /**
     * complete(T t)会立即执行。但是complete(T t)只能调用一次，后续的重复调用会失效
     */
    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName() + " - Hello");

        future.complete("aaa");
        System.out.println("========== " + future.get());
    }


    /////////////////////////////////////////// whenComplete -> BiConsumer /////////////////////////////////////////////////

    /**
     * 无返回值
     */
    @Test
    public void testWhenComplete() {

        String error = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if (1 == 1) {
            //     throw new RuntimeException("Error");
            // }
            return Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }).whenComplete((s, e) -> {
            System.out.println("whenComplete result: " + s);
            System.out.println("whenComplete error: " + e);
        }).join();

        System.out.println(error);
    }

    /////////////////////////////////////////// handle -> BiFunction /////////////////////////////////////////////////

    /**
     * 有返回值
     */
    @Test
    public void testHandle() {
        String error = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if (1 == 1) {
            //     throw new RuntimeException("Error");
            // }
            return Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }).handle((s, e) -> {
            if (e != null) {
                return "error";
            }
            return s;
        }).join();
        System.out.println(error);
    }

    /////////////////////////////////////////// thenAccept -> Consumer /////////////////////////////////////////////////


    /**
     * thenAccept()是针对结果进行消耗，因为他的入参是Consumer，有入参无返回值
     */
    @Test
    public void testAccept() {
        for (int i = 0; i < 1000; i++) {
            CompletableFuture.supplyAsync(() -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .thenAccept(s -> System.out.println(Thread.currentThread().getName() + " => now is " + s))
                    .join();
        }
    }


}
