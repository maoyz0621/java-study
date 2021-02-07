/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:11  Inc. All rights reserved.
 */
package com.myz.design.state.base;

/**
 * @author maoyz
 */
public class StateClient {

    /* 类似开关切换 */
    public static void main(String[] args) {
        StateContext context = new StateContext(new ConcreteStateA());

        context.execute();
        context.execute();
        context.execute();
        context.execute();
        context.execute();
    }
}