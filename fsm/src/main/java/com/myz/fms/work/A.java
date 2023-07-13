/**
 * Copyright 2023 Inc.
 **/
package com.myz.fms.work;

import com.myz.fms.core.FsmEventHandler;
import com.myz.fms.enums.OrderChangeContext;
import com.myz.fms.enums.OrderState;
import com.myz.fms.enums.OrderStateChangeEvent;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class A {

    AbcAbstractFsmEngine abcAbstractFsmEngine;

    public void test(OrderChangeContext orderChangeContext, OrderStateChangeEvent changeEvent) {
        FsmEventHandler<OrderState, OrderStateChangeEvent, OrderChangeContext> handler = abcAbstractFsmEngine.getHandler(orderChangeContext.getFromState(), changeEvent);

        OrderState next = abcAbstractFsmEngine.next(orderChangeContext, orderChangeContext.getFromState(), changeEvent);


        boolean fire = abcAbstractFsmEngine.fire(orderChangeContext.getFromState(), changeEvent, orderChangeContext);
    }
}