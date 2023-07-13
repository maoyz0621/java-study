/**
 * Copyright 2022 Inc.
 **/
package com.myz.fms.core;

/**
 * 注册关系：AbstractFsmEventHandler关系- FsmState + FsmEvent
 *
 * @author maoyz0621 on 2022/2/23
 * @version v1.0
 */
public abstract class AbstractFsmEventHandler<S extends FsmState, E extends FsmEvent, C extends FsmContext<S, E, C>> implements FsmEventHandler<S, E, C> {
    protected FiniteStateMachine<S, E, C> fsm;

    @Override
    public boolean handle(C context, E event) {
        AbstractFsmEventHandler<S, E, C> self = self();
        if (self.beforeHandle(context)) {
            S to = this.fsm.next(context, context.getFromState(), event);
            context.setToState(to);
            if (self.doHandle(context)) {
                return self.afterHandle(context);
            }
        }
        return true;
    }

    /**
     * 前置操作
     *
     * @param context
     */
    protected abstract boolean beforeHandle(C context);

    /**
     * 真正执行
     *
     * @param context
     * @return
     */
    protected abstract boolean doHandle(C context);

    /**
     * 后置操作
     *
     * @param context
     */
    protected abstract boolean afterHandle(C context);

    @Override
    public void bind(FiniteStateMachine<S, E, C> machine) {
        this.fsm = machine;
    }

    protected AbstractFsmEventHandler<S, E, C> self() {
        return this;
    }
}