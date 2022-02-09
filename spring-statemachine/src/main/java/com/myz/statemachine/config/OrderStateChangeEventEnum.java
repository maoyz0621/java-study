/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

/**
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
public enum OrderStateChangeEventEnum {
    // 支付
    PAYED,
    // 发货
    DELIVERED,
    // 确认收货
    RECEIVED,
    ;
}