/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 12:15  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public class RewardStateContext {

    // private final static RewardState STATEA;

    RewardState currentRewardState;

    public void echo(RewardStateContext context) {
        currentRewardState.doReward(context);
    }

    public void setCurrentRewardState(RewardState currentRewardState) {
        this.currentRewardState = currentRewardState;
    }
}