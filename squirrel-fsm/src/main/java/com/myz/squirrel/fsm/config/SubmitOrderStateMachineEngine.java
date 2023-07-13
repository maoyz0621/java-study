/**
 * Copyright 2023 Inc.
 **/
package com.myz.squirrel.fsm.config;

import com.myz.squirrel.fsm.core.FsmState;
import com.myz.squirrel.fsm.spring.AbstractStateMachineEngine;

/**
 * @author maoyz0621 on 2023/3/1
 * @version v1.0
 */
public class SubmitOrderStateMachineEngine extends AbstractStateMachineEngine<SubmitOrderStateMachine> {

    // todo
    @Override
    protected boolean accept(FsmState fsmState) {
        return false;
    }
}