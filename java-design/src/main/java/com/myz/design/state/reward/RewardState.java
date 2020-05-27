/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 11:59  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public abstract class RewardState {

    protected RewardStateContext stateContext;

    public RewardState(RewardStateContext stateContext) {
        this.stateContext = stateContext;
    }

    public abstract void doReward(RewardStateContext context);

    public void setStateContext(RewardStateContext stateContext) {
        this.stateContext = stateContext;
    }
}