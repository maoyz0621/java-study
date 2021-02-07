/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-11 12:05  Inc. All rights reserved.
 */
package com.myz.java.study.base.final0;

/**
 * @author maoyz
 */
public class FinalExample {

    private int i;
    private final int j;
    static FinalExample obj;

    public FinalExample() {
        i = 1;                        // 写普通域
        j = 2;
    }

    public static void writer() {    // 写线程 A 执行
        obj = new FinalExample();
    }

    public static void reader() {       // 读线程 B 执行
        FinalExample object = obj;       // 读对象引用
        if (object != null) {
            int a = object.i;                // 读普通域
            int b = object.j;
            System.out.println("a=" + a + "; b=" + b);// 读 final 域
        }

    }


    public static void main(String[] args) {
        new Thread(() -> FinalExample.writer(), "writer: ").start();
        new Thread(() -> FinalExample.reader(), "reader: ").start();
    }
}