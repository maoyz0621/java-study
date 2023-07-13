/**
 * Copyright 2022 Inc.
 **/
package com.myz.fms.core;

/**
 * FsmState、FsmEvent、version 注册 FsmEventHandler
 * @author maoyz0621 on 2022/2/23
 * @version v1.0
 */
public interface FsmEventHandler<S extends FsmState, E extends FsmEvent, C extends FsmContext<S, E, C>> {

    boolean handle(C context, E event);

    void bind(FiniteStateMachine<S, E, C> machine);
}