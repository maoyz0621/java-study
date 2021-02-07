/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:07  Inc. All rights reserved.
 */
package com.myz.design.state.base;

/**
 * @author maoyz
 */
public class ConcreteStateB extends State {

    @Override
    public void handle(StateContext context) {
        System.out.println("ConcreteStateB");
        // 切换状态
        context.setState(new ConcreteStateA());
    }
}