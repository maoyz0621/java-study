/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.base.exception;

/**
 * @author maoyz0621 on 20-4-16
 * @version v1.0
 */
public class ExceptionTest {

    public static void main(String[] args) {
        // new ExceptionTest().t2();   // t2 error
        // new ExceptionTest().t3();   // t1 error
        // new ExceptionTest().t4();   // t1 error
        // new ExceptionTest().t5();   // java.lang.RuntimeException: t1 error \n Caused by: java.lang.RuntimeException: t1 error

        new ExceptionTest().innerForeach();
        // new ExceptionTest().outterForeach();
    }

    /**
     * 抛出t1异常
     */
    public void t1() {
        throw new RuntimeException("t1 error");
    }

    /**
     * 捕获到Exception, catch t1, throw t2
     */
    public void t2() {
        try {
            t1();
        } catch (Exception e) {
            throw new RuntimeException("t2 error");
        }
    }

    /**
     * 捕获非t1异常，NullPointerException, catch t1, throw t3
     * 没有捕获到t1异常，就继续向上抛
     */
    public void t3() {
        try {
            t1();
        } catch (NullPointerException e) {
            throw new RuntimeException("t3 error");
        }
    }

    /**
     * 　捕获t1错误message
     */
    public void t4() {
        try {
            t1();
        } catch (Exception e) {
            // t1 error
            System.err.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 捕获了t1全部错误栈信息
     */
    public void t5() {
        try {
            t1();
        } catch (Exception e) {
            // java.lang.RuntimeException: t1 error
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 循环内的 try-catch 在发生异常之后，可以继续执行循环；
     * throw 循环终止
     */
    public void innerForeach() {
        for (int i = 0; i < 5; i++) {
            try {
                if (i == 3) {
                    throw new RuntimeException("error");
                }
            } catch (RuntimeException e) {
                // throw e;
            }
            System.out.println(i);
        }
    }

    /**
     * 循环外的 try-catch 在发生异常之后会终止循环。
     * throw 循环终止
     */
    public void outterForeach() {
        try {
            for (int i = 0; i < 5; i++) {
                if (i == 3) {
                    throw new RuntimeException("error");
                }
                System.out.println(i);
            }
        } catch (RuntimeException e) {
            // throw e;
        }
    }
}
