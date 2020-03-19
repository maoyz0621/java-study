/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda;

/**
 * 编译之后，生成2个class文件，MainAnonymousClassTest$1.class（匿名内部类产生的）、MainAnonymousClassTest.class
 *
 * @author maoyz0621 on 19-3-24
 * @version v1.0
 */
public class MainAnonymousClassTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类： ");
            }
        }).start();
    }
}
