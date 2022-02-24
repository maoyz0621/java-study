/**
 * Copyright 2022 Inc.
 **/
package com.myz.fms.core;

/**
 * @author maoyz0621 on 2022/2/23
 * @version v1.0
 */
public interface FiniteStateMachine<S extends FsmState, E extends FsmEvent, C extends FsmContext<S, E, C>> {

    /**
     * 设置下一个state
     *
     * @param context
     * @param fromState
     * @param event
     * @return
     */
    S next(C context, S fromState, E event);
}