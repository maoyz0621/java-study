/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 15:11  Inc. All rights reserved.
 */
package com.myz.design.state.reward;

/**
 * 返奖状态
 * 待校验状态，预返奖状态，待返奖状态，成功状态，失败状态，返奖补偿阶段
 *
 * @author maoyz
 */
public enum StatusEnum {
    OrderCheckState(1),
    BeforeRewardCheckState(2),
    SendRewardState(3),
    RewardSuccessState(4),
    CompensateRewardState(5),
    RewardFailedState(-1);

    StatusEnum(int index) {

    }
}