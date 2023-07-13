/**
 * Copyright 2023 Inc.
 **/
package com.myz.fms.enums;

import com.myz.fms.core.FsmContext;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class OrderChangeContext implements FsmContext<OrderState, OrderStateChangeEvent, OrderChangeContext> {
    @Override
    public OrderState getFromState() {
        return null;
    }

    @Override
    public OrderChangeContext setFromState(OrderState state) {
        return null;
    }

    @Override
    public OrderState getToState() {
        return null;
    }

    @Override
    public OrderChangeContext setToState(OrderState state) {
        return null;
    }

    @Override
    public OrderStateChangeEvent getEvent() {
        return null;
    }

    @Override
    public OrderChangeContext setEvent(OrderStateChangeEvent event) {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }
}