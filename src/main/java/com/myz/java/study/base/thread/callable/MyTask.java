package com.myz.java.study.base.thread.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable
 * FutureTask(Callable<V> callable)使用
 * get() 取值
 * cancel(boolean mayInterruptIfRunning) 取消任务
 *
 * @author maoyz on 18-3-20.
 */
public class MyTask implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(MyTask.class);

    @Override
    public String call() throws Exception {
        return "你好";
    }

    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<String>(new MyTask());
        new Thread(task).start();
        try {
            logger.debug("{}", task.isCancelled());
            logger.debug(task.get());
            task.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
