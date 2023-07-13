/**
 * Copyright 2023 Inc.
 **/
package com.myz.fms.work;

import com.myz.fms.core.AbstractFsmEngine;
import com.myz.fms.core.FsmEventHandler;
import com.myz.fms.enums.OrderChangeContext;
import com.myz.fms.enums.OrderState;
import com.myz.fms.enums.OrderStateChangeEvent;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class AbcAbstractFsmEngine extends AbstractFsmEngine<OrderState, OrderStateChangeEvent, OrderChangeContext> {

    @Override
    public OrderState next(OrderChangeContext context, OrderState fromState, OrderStateChangeEvent event) {
        switch (fromState) {
            case STATE_INIT:
                switch (event) {
                    case EVENT_ASSIGN:
                        return OrderState.STATE_DISPATCHING;

                    case EVENT_CANCEL:
                        return OrderState.STATE_DISPATCH_FAILED;
                    default:
                        break;
                }

            case STATE_DISPATCHING:

            case STATE_FINISH:

            case STATE_DISPATCH_FAILED:

        }
        return null;
    }

    @Override
    public FsmEventHandler<OrderState, OrderStateChangeEvent, OrderChangeContext> getHandler(OrderState state, OrderStateChangeEvent event) {
        return super.getHandler(state, event);
    }
}