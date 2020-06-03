package com.myz.cglib;

/**
 * 目标类，未实现接口
 *
 * @author maoyz on 18-3-9.
 */
public class TargetObject {

    public String method(String arg) {
        System.out.println("目标方法在执行..." + arg);
        return arg;
    }
}
