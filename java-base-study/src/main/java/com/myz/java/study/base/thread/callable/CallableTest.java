package com.myz.java.study.base.thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 实现Callable
 * FutureTask(Callable<V> callable)使用  implements RunnableFuture<V> ; RunnableFuture<V> extends Runnable, Future<V>
 * get() 取值
 * cancel(boolean mayInterruptIfRunning) 取消任务
 * <p>
 * Future保存异步计算的结果,可以在我们执行任务时去做其他工作
 * cancel(boolean mayInterruptIfRunning)：试图取消执行的任务，参数为true时直接中断正在执行的任务，否则直到当前任务执行完成，成功取消后返回true，否则返回false
 * isCancel()：判断任务是否在正常执行完前被取消的，如果是则返回true
 * isDone()：判断任务是否已完成
 * get()：等待计算结果的返回，如果计算被取消了则抛出
 * get(long timeout,TimeUtil unit)：设定计算结果的返回时间，如果在规定时间内没有返回计算结果则抛出TimeOutException
 *
 * @author maoyz on 18-3-20.
 */
@Slf4j
public class CallableTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "你好";
    }

    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<>(new CallableTest());

        new Thread(task).start();
        try {
            log.debug("cancel start = {}", task.isCancelled());

            // 雷士自旋锁
            while (task.isDone()) {
                System.out.println("等待好无聊........................");
            }

            log.debug(task.get(60, TimeUnit.SECONDS));
            log.debug("{}", task.isCancelled());
            task.cancel(true);

            log.debug("cancel end = {}", task.isCancelled());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("", e);
            task.cancel(true);
        }
    }
}
