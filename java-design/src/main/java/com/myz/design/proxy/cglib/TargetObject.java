package com.myz.design.proxy.cglib;

/**
 * 目标类，未实现接口
 *
 * @author maoyz on 18-3-9.
 */
public class TargetObject {

    /**
     * 该方法做了其他操作
     */
    public String method(String arg) {
        return arg;
    }

    /**
     * 原路返回
     */
    public String noMethod(String arg) {
        return arg;
    }

    /**
     * 异常
     */
    public int error() {
        return 1 / 0;
    }

    /**
     * final修饰的方法不能被cglib代理
     */
    public final String finalMethod() {
        return "final";
    }
}
