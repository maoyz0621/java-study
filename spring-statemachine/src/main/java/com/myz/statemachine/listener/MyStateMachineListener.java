/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.listener;

import com.myz.statemachine.config.OrderStateChangeEventEnum;
import com.myz.statemachine.config.OrderStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 * @author maoyz0621 on 2022/2/10
 * @version v1.0
 */
@Slf4j
public class MyStateMachineListener extends StateMachineListenerAdapter<OrderStateEnum, OrderStateChangeEventEnum> {

    @Override
    public void stateChanged(State<OrderStateEnum, OrderStateChangeEventEnum> from, State<OrderStateEnum, OrderStateChangeEventEnum> to) {
        if (from != null && to != null) {
            log.info("---------------------- stateChanged = {}, {} ----------------------", from.getId(), to.getId());
        }
    }
}