package com.myz.java.study.java8.future;

import java.util.concurrent.ForkJoinPool;

/**
 * ForkJoinPool -> ExecutorService的一个实现
 * ForkJoinPool并不会为每个任务都创建一个单独的线程，它会使用一个特殊的数据结构double-ended queue来存储任务。
 * 这样的结构可以方便的进行工作窃取（work-stealing）。
 * 什么是work-stealing呢？
 * 默认情况下，work thread从分配给自己的那个队列头中取出任务。如果这个队列是空的，那么这个work thread会从其他的任务队列尾部取出任务来执行，或者从全局队列中取出。这样的设计可以充分利用work thread的性能，提升并发能力。
 * <p>
 * ForkJoinWorkerThread
 * <p>
 * newWorkStealingPool
 * ForkJoinTask
 * RecursiveAction ForkJoinTask的一个子类，含任务执行结果
 * RecursiveTask ForkJoinTask的另一个子类，不含任务执行结果
 *
 * @author maoyz
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println(forkJoinPool.getActiveThreadCount());
        System.out.println(forkJoinPool.getRunningThreadCount());
        // 处理器核心 2个
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(2);

    }
}
