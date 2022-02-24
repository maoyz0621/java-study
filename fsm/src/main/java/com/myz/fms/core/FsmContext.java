/**
 * Copyright 2022 Inc.
 **/
package com.myz.fms.core;

/**
 * @author maoyz0621 on 2022/2/23
 * @version v1.0
 */
public interface FsmContext<S extends FsmState, E extends FsmEvent, C extends FsmContext<S, E, C>> {

    S getFromState();

    C setFromState(S state);

    S getToState();

    C setToState(S state);

    E getEvent();

    C setEvent(E event);

    String getVersion();
}