package com.myz.spring.aop.annotation;

import org.springframework.stereotype.Component;

public class Calculator {

    public int add(int x, int y) {
        System.out.println("add()");
        return x + y;

    }

    public float divide(int x, int y) {
        System.out.println("divide()");
        return x / y;
    }

    public void noArgs() {
        System.out.println("noArgs()");
    }

}
