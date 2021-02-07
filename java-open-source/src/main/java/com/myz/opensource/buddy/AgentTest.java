/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.buddy;

/**
 * @author maoyz0621 on 2020/11/23
 * @version v1.0
 */
public class AgentTest {

    // 添加运行参数
    // -javaagent:F:\Work\IDEA\java-study\java-buddy\target\java-buddy-1.0.jar=test
    public static void main(String[] args) throws Exception {
        AgentTest test = new AgentTest();
        test.fun1();
        test.fun2();

    }

    private void fun1() throws Exception {
        System.out.println("this is fun 1.");
        Thread.sleep(500);
    }

    private void fun2() throws Exception {
        System.out.println("this is fun 2.");
        Thread.sleep(500);
    }
}