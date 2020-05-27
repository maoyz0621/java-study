/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:06  Inc. All rights reserved.
 */
package com.myz.design.state.base;

/**
 * @author maoyz
 */
public class StateContext {

    private State state;

    public StateContext(State state) {
        this.state = state;
    }

    public void execute() {
        this.state.handle(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}