/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 15:46  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public class CompensateRewardState extends RewardState {

    public CompensateRewardState(RewardStateContext stateContext) {
        super(stateContext);
    }

    @Override
    public void doReward(RewardStateContext context) {
        compensateReward(context);  //返奖失败，需要对用户进行返奖补偿
    }

    private void compensateReward(RewardStateContext context) {

    }
}