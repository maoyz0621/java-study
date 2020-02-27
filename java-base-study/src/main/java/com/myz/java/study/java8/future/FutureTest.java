/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.java8.future;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture 和 CompletionStage
 *
 * @author maoyz0621 on 20-2-26
 * @version v1.0
 */
public class FutureTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * thenApply()进行变换
     */
    @Test
    public void testApply() {
        String join = CompletableFuture.supplyAsync(() -> LocalDateTime.now().format(formatter))
                .thenApply((s) -> "now is " + s)
                .join();
        System.out.println(join);
    }


    /**
     * thenAccept()是针对结果进行消耗，因为他的入参是Consumer，有入参无返回值
     */
    @Test
    public void testAccept() {
        for (int i = 0; i < 1000; i++) {
            CompletableFuture.supplyAsync(() -> LocalDateTime.now().format(formatter))
                    .thenAccept(s -> System.out.println(Thread.currentThread().getName() + " => now is " + s))
                    .join();
        }
    }


    /**
     * thenRun()它的入参是一个Runnable的实例，表示当得到上一步的结果时的操作。
     */
    @Test
    public void testRun() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {

            }
            return LocalDateTime.now().format(formatter);
        }).thenRun(() -> System.out.println(Thread.currentThread().getName() + " => now is " + LocalDateTime.now().format(formatter))).join();
    }

    ///////////////////////////////////// CompletionStage ////////////////////////////////////////

    /**
     * CompletionStage.thenCombine()多个结果进行转化后返回
     */
    @Test
    public void testCombine() {
        System.out.println(LocalDateTime.now());

        String join = CompletableFuture.supplyAsync(() -> sleep1(3))
                .thenCombine(CompletableFuture.supplyAsync(() -> sleep2(5)),
                        (s1, s2) -> s1 + " =====　" + s2)
                .join();
        System.out.println(LocalDateTime.now());

        System.out.println(join);
    }

    /**
     * CompletionStage.thenAcceptBoth()结合多个CompletionStage的结果，进行消耗
     */
    @Test
    public void testAcceptBoth() {
        System.out.println(LocalDateTime.now());

        CompletableFuture.supplyAsync(() -> sleep1(2))
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> sleep2(4)),
                        (s1, s2) -> System.out.println(s1 + " =====　" + s2))
                .join();

        System.out.println(LocalDateTime.now());
    }

    /**
     * CompletionStage.runAfterBothAsync()不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）
     */
    @Test
    public void testAfterBothAsync() {
        System.out.println(LocalDateTime.now());

        CompletableFuture.supplyAsync(() -> sleep1(4))
                .runAfterBothAsync(CompletableFuture.supplyAsync(() -> sleep2(1)),
                        () -> System.out.println(Thread.currentThread().getName()))
                .join();

        System.out.println(LocalDateTime.now());
    }

    /**
     * CompletionStage.applyToEither() 谁计算的快，就使用那个CompletionStage的结果进行下一步的转化操作
     * 总会碰到有两种渠道完成同一个事情，所以就可以调用这个方法，找一个最快的结果进行处理
     */
    @Test
    public void testApplyToEither() {
        System.out.println(LocalDateTime.now());

        String join = CompletableFuture.supplyAsync(() -> sleep1(5))
                .applyToEither(CompletableFuture.supplyAsync(() -> sleep2(6)),
                        (s) -> Thread.currentThread().getName() + " ==> " + s)
                .join();

        System.out.println(LocalDateTime.now());
        System.out.println(join);
    }


    /**
     * CompletionStage.acceptEither() 谁计算的快，就使用那个CompletionStage的结果进行下一步的消耗操作
     */
    @Test
    public void testAcceptEither() {
        System.out.println(LocalDateTime.now());

        CompletableFuture.supplyAsync(() -> sleep1(2))
                .acceptEither(CompletableFuture.supplyAsync(() -> sleep2(4)),
                        (s1) -> System.out.println(Thread.currentThread().getName() + " ==> " + s1))
                .join();
        System.out.println(LocalDateTime.now());
    }

    /**
     * CompletionStage.runAfterEither()  任何一个完成了都会执行下一步的操作（Runnable）
     */
    @Test
    public void testRunAfterEither() {
        System.out.println(LocalDateTime.now());

        CompletableFuture.supplyAsync(() -> sleep1(2))
                .runAfterEither(CompletableFuture.supplyAsync(() -> sleep2(3)),
                        () -> System.out.println(Thread.currentThread().getName() + " ==> "))
                .join();
        System.out.println(LocalDateTime.now());
    }

    /**
     * exceptionally() 当运行时出现了异常时进行补偿
     */
    @Test
    public void testExceptionally() {
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("Error");
            }
            return Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }).exceptionally((e) -> {
            System.out.println(e);
            return "Sorry";
        }).join();
        System.out.println(join);
    }

    /**
     * whenComplete()当运行完成时，对结果的记录。
     * 这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。
     * 这里为什么要说成记录，因为这几个方法都会返回CompletableFuture，当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。
     * 所以不会对结果产生任何的作用。
     * <p>
     * 如果使用了exceptionally，就会对最终的结果产生影响，它没有口子返回如果没有异常时的正确的值
     */
    @Test
    public void testWhenComplete() {

        String error = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if (1 == 1) {
            //     throw new RuntimeException("Error");
            // }
            return Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }).whenComplete((s, e) -> {
            System.out.println("whenComplete: " + s);
            System.out.println("whenComplete: " + e);
        }).exceptionally((e) -> {
            System.out.println(e);
            return "Sorry";
        }).join();

        System.out.println(error);
    }


    /**
     * handle()
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

    private String sleep2(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private String sleep1(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {

        }
        return Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
