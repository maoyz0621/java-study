/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 15:44  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public class OrderCheckState extends RewardState {

    public OrderCheckState(RewardStateContext stateContext) {
        super(stateContext);
    }

    @Override
    public void doReward(RewardStateContext context) {
        orderCheck(context);
    }

    private void orderCheck(RewardStateContext context) {
        context.setCurrentRewardState(new BeforeRewardCheckState(context));
        doReward(context);
    }
}