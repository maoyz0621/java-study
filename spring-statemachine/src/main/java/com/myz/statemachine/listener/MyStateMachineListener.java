/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.listener;

import com.myz.statemachine.enums.OrderState;
import com.myz.statemachine.enums.OrderStateChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author maoyz0621 on 2022/2/10
 * @version v1.0
 */
@Slf4j
public class MyStateMachineListener extends StateMachineListenerAdapter<OrderState, OrderStateChangeEvent> {

    /**
     * state变化
     *
     * @param from
     * @param to
     */
    @Override
    public void stateChanged(State<OrderState, OrderStateChangeEvent> from, State<OrderState, OrderStateChangeEvent> to) {
        if (from != null && to != null) {
            log.info("---------------------- StateMachineListenerAdapter stateChanged = {}, {} ----------------------", from.getId(), to.getId());
        }
    }

    @Override
    public void transition(Transition<OrderState, OrderStateChangeEvent> transition) {
        if (transition != null) {
            log.info("---------------------- StateMachineListenerAdapter transition = {} - {} ----------------------", transition.getSource(), transition.getTarget());
        }
    }

    @Override
    public void eventNotAccepted(Message<OrderStateChangeEvent> event) {
        log.warn("---------------------- StateMachineListenerAdapter eventNotAccepted = {} ----------------------", event);
    }
}