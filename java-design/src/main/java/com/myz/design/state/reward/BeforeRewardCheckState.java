/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 15:47  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public class BeforeRewardCheckState extends RewardState {
    public BeforeRewardCheckState(RewardStateContext stateContext) {
        super(stateContext);
    }

    @Override
    public void doReward(RewardStateContext context) {

    }
}