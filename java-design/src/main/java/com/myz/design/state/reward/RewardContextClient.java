/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 17:17  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * @author maoyz
 */
public class RewardContextClient {

    public static void main(String[] args) {
        RewardStateContext stateContext = new RewardStateContext();
        stateContext.setCurrentRewardState(new OrderCheckState(stateContext));
        stateContext.echo(stateContext);
    }
}