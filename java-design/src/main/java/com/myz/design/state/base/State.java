/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:06  Inc. All rights reserved.
 */
package com.myz.design.state.base;

/**
 * 抽象状态角色
 * @author maoyz
 */
public abstract class State {

    public abstract void handle(StateContext context);
}