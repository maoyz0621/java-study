/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 15:49  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public class RewardSuccessState extends RewardState {
    public RewardSuccessState(RewardStateContext stateContext) {
        super(stateContext);
    }

    @Override
    public void doReward(RewardStateContext context) {

    }
}