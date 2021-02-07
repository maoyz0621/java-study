/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda;

/**
 * 不会产生新的类
 * 编译之后，只生成1个class文件.MainLambdaTest.class，**Lambda表达式被封装成主类的私有方法，之后通过invokedynamic指令调用**
 *
 * @author maoyz0621 on 19-3-24
 * @version v1.0
 */
public class MainLambdaTest {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Lambda实现Runnable");

        }).start();
    }
}
