/**
 * Copyright 2023 Inc.
 **/
package com.myz.squirrel.fsm.config;

import com.myz.squirrel.fsm.core.FsmContext;
import com.myz.squirrel.fsm.core.FsmEvent;
import com.myz.squirrel.fsm.core.FsmState;
import com.myz.squirrel.fsm.spring.BaseOrderStateMachine;
import org.springframework.context.ApplicationContext;
import org.squirrelframework.foundation.fsm.annotation.State;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.States;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;

/**
 * 基于注解的方式
 * 注解一：@State，
 * 注解er：@Transit
 *
 * @author maoyz0621 on 2023/2/27
 * @version v1.0
 */
@States({
        @State(name = "", entryCallMethod = "", exitCallMethod = "", initialState = true),
        @State(name = "", entryCallMethod = "", exitCallMethod = "", initialState = true)
})
@Transitions({
        @Transit(from = "", to = "", on = "", callMethod = "submitOrder"),
        @Transit(from = "", to = "", on = "", callMethod = "")
})
@StateMachineParameters(stateType = FsmState.class, eventType = FsmEvent.class, contextType = FsmContext.class)
public class SubmitOrderStateMachine extends BaseOrderStateMachine {

    public SubmitOrderStateMachine(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public void submitOrder(FsmState fromState, FsmState toState, FsmEvent fsmEvent, FsmContext fsmContext) {

    }

}