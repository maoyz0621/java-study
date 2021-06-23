package com.myz.java.study.java8.future;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask
 * fork()：开启一个新线程（或是重用线程池内的空闲线程），将任务交给该线程处理
 * join()：等待该任务的处理线程处理完毕，获得返回值
 *
 * 疑问：当任务分解得越来越细时，所需要的线程数就会越来越多，而且大部分线程处于等待状态？
 *
 * @author maoyz
 */
public class ForkJoinTaskTest {

    public static void main(String[] args) throws Exception {
        RecursiveTaskTest recursiveTaskTest = new RecursiveTaskTest(10);

        System.out.println(recursiveTaskTest.invoke());
    }

    /**
     * ForkJoinTask 实现一 RecursiveAction,无返回值
     */
    class SortTask extends RecursiveAction {
        private final long[] array;
        private final int lo, hi;

        public SortTask(long[] array) {
            this(array, 0, array.length);
        }

        public SortTask(long[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        /**
         * 临界值
         */
        static final int THRESHOLD = 1000;

        @Override
        protected void compute() {
            if (hi - lo < THRESHOLD) {
                sort(lo, hi);
            } else {
                int mid = (lo + hi) >>> 1;
                invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
                merge(lo, mid, hi);
            }
        }

        /**
         * 结果合并
         *
         * @param lo
         * @param mid
         * @param hi
         */
        private void merge(int lo, int mid, int hi) {

        }

        /**
         * 简单分组
         *
         * @param lo
         * @param hi
         */
        private void sort(int lo, int hi) {

        }
    }

    /**
     * ForkJoinTask 实现二 RecursiveTask,有返回值
     */
    static class RecursiveTaskTest extends RecursiveTask<Integer> {

        final int i;

        public RecursiveTaskTest(int i) {
            this.i = i;
        }

        @Override
        protected Integer compute() {
            if (i <= 1) {
                return i;
            }
            RecursiveTaskTest f1 = new RecursiveTaskTest(i - 1);
            // 执行子任务
            f1.fork();
            RecursiveTaskTest f2 = new RecursiveTaskTest(i - 2);
            return f2.compute() + f1.join();
        }
    }

    /**
     * ForkJoinTask 实现三 CountedCompleter
     */
    class CountedCompleterTest extends CountedCompleter {

        @Override
        public void compute() {

        }
    }
}


