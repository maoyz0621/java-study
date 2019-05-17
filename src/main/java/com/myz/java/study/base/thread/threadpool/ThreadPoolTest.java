package com.myz.java.study.base.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * newCachedThreadPool()
 * newScheduledThreadPool(n)
 * newSingleThreadExecutor()
 * newFixedThreadPool()
 * <p>
 * ExecutorService threadPool = Executors.newFixedThreadPool(n)创建n个空线程池
 * threadPool.execute(Runnable command)设置线程池要执行的任务
 *
 * @author maoyz
 */

public class ThreadPoolTest {

    public static void main(String[] args) {
        // new ThreadPoolTest().testCachedThreadPool();
        // new ThreadPoolTest().testFixedThreadPool();
        // new ThreadPoolTest().testScheduledThreadPool();
        new ThreadPoolTest().testSingleThreadExecutor();
    }


    /**
     * newCachedThreadPool()，可缓存线程池
     * 创建可一段时间内重复利用的线程池，常用于不知道具体的任务数量，但是还需要进行并行处理的情况，
     * 如springboot @Aysnc就可以指定使用这个线程池，来进行一些埋点等的各种业务的异步处理
     * 创建一个可缓存的线程池，如果当前线程池的规模超出了处理需求，将回收空的线程；当需求增加时，会增加线程数量；线程池规模无限制。
     */
    public void testCachedThreadPool() {
        // newCachedThreadPool()，可缓存线程池,核心线程池数为0，最大线程池数为Integer.MAX_VALUE,
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        final int size = 45;
        // 创建n个线程
        for (int i = 0; i < size; i++) {
            int temp = i;
            // execute(Runnable command)设置线程池要执行的任务
            cachedThreadPool.execute(() -> System.out.println("可缓存线程: " + Thread.currentThread().getName() + ":" + temp));
        }
        cachedThreadPool.shutdown();
    }


    /**
     * newFixedThreadPool 可重用固定线程数的线程池
     * 创建固定数量的线程的线程池，可以控制最大并发数，常用于知道具体任务的数量，需要进行多线程的操作，
     * 如批量插入数据库任务，需要进行10万条数据分页，每1万条数据一页，配置一个线程处理，一共配置10个线程，进行并行批量插入，就可以使用这个线程池来进行，大大减少响应时间
     */
    public void testFixedThreadPool() {
        // newFixedThreadPool 可重用固定线程数的线程池，核心线程池数为n，最大线程池数也为n
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        fixedThreadPool.execute(() -> test1());

        fixedThreadPool.execute(() -> test2());

        fixedThreadPool.execute(() -> test3());

        fixedThreadPool.execute(() -> test2());

        fixedThreadPool.execute(() -> test4());

        // 关闭资源
        // fixedThreadPool.shutdown();

        testPool(fixedThreadPool);
    }

    private void testPool(ExecutorService fixedThreadPool) {
        for (int i = 0; i < 30; i++) {

            final int temp = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("可重用固定线程数的线程: " + Thread.currentThread().getName() + ":" + temp);
                }
            });
        }
        // 不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
        fixedThreadPool.shutdown();
    }

    private void test2() {
        int i = 1 / 1;
        System.out.println("可重用固定线程数的线程: " + Thread.currentThread().getName() + ":     2");
    }

    private void test4() {
        int i = 1 / 1;
        System.out.println("可重用固定线程数的线程: " + Thread.currentThread().getName() + ":     5");
    }

    private void test3() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
        }
        System.out.println("可重用固定线程数的线程: " + Thread.currentThread().getName() + ":      3");
    }

    private void test1() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
        }
        System.out.println("可重用固定线程数的线程: " + Thread.currentThread().getName() + ":       1");
    }


    /**
     * newScheduledThreadPool 定时线程池，可执行定时任务
     */
    public void testScheduledThreadPool() {
        // newScheduledThreadPool 定长线程池，可执行定时任务，最大线程池数为Integer.MAX_VALUE
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        final int size = 10;
        for (int i = 0; i < size; i++) {
            final int temp = i;
            scheduledThreadPool.schedule(() -> System.out.println("定长线程池: " + Thread.currentThread().getName() + ":" + temp),
                    5, TimeUnit.SECONDS);
        }
        scheduledThreadPool.shutdown();
    }


    /**
     * newSingleThreadExecutor 单线程池
     * 这个线程池可以在线程死后（或发生异常时）重新启动一个线程来替代原来的线程继续执行下去！
     * 确保任务对了，串行执行
     */
    public void testSingleThreadExecutor() {

        // newSingleThreadExecutor 单线程池,核心线程池数为1，最大线程池数也为1
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        final int size = 10;
        for (int i = 0; i < size; i++) {
            int temp = i;
            singleThreadExecutor.execute(() -> System.out.println("单线程池: " + Thread.currentThread().getName() + "-->" + temp));
        }
        singleThreadExecutor.shutdown();
    }

}
