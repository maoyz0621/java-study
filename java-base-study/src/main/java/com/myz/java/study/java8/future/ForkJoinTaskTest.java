package com.myz.java.study.java8.future;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask
 *
 * @author maoyz
 */
public class ForkJoinTaskTest {

    public static void main(String[] args) {

    }
}


/**
 * ForkJoinTask 实现一 RecursiveAction
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
 * ForkJoinTask 实现二 RecursiveTask
 */
class RecursiveTaskTest extends RecursiveTask {

    @Override
    protected Object compute() {
        return null;
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