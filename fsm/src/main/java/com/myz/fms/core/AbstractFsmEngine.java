/**
 * Copyright 2022 Inc.
 **/
package com.myz.fms.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author maoyz0621 on 2022/2/23
 * @version v1.0
 */
public abstract class AbstractFsmEngine<S extends FsmState, E extends FsmEvent, C extends FsmContext<S, E, C>> implements FiniteStateMachine<S, E, C> {

    private final Map<AbstractFsmEngine<S, E, C>.FsmKey, FsmEventHandler<S, E, C>> registry = new HashMap<>();
    private FsmEventHandler<S, E, C> defaultHandler;

    public AbstractFsmEngine(FsmEventHandler<S, E, C> handler) {
        this.defaultHandler = handler;
        handler.bind(this);
    }

    public boolean fire(S state, E event, C context) {
        FsmEventHandler<S, E, C> handler = getHandler(state, event, context.getVersion());
        if (handler == null) {
            return false;
        }
        context.setFromState(state);
        context.setEvent(event);
        // 执行
        return handler.handle(context, event);
    }

    /**
     * FsmState、FsmEvent 注册 FsmEventHandler
     *
     * @param state
     * @param event
     * @param handler
     */
    public void register(S state, E event, FsmEventHandler<S, E, C> handler) {
        registry.put(new AbstractFsmEngine<S, E, C>.FsmKey(state, event), handler);
        handler.bind(this);
    }

    /**
     * FsmState、FsmEvent、version 注册 FsmEventHandler
     *
     * @param state
     * @param event
     * @param version
     * @param handler
     */
    public void register(S state, E event, String version, FsmEventHandler<S, E, C> handler) {
        registry.put(new AbstractFsmEngine<S, E, C>.FsmKey(state, event, version), handler);
        handler.bind(this);
    }

    public FsmEventHandler<S, E, C> getHandler(S state, E event) {
        return getHandler(state, event, null);
    }

    public FsmEventHandler<S, E, C> getHandler(S state, E event, String version) {
        FsmEventHandler<S, E, C> fsmEventHandler = registry.get(new FsmKey(state, event, version));
        if (fsmEventHandler == null) {
            FsmEventHandler<S, E, C> eventHandler = registry.get(new FsmKey(state, event));
            return eventHandler == null ? defaultHandler : eventHandler;
        }

        return fsmEventHandler;
    }


    private class FsmKey {
        private final S state;
        private final E event;
        private String version;

        public FsmKey(S state, E event) {
            this.state = state;
            this.event = event;
        }

        public FsmKey(S state, E event, String version) {
            this.state = state;
            this.event = event;
            this.version = version;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("FsmKey{");
            sb.append("state=").append(state);
            sb.append(", event=").append(event);
            sb.append(", version='").append(version).append('\'');
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AbstractFsmEngine.FsmKey)) return false;
            FsmKey fsmKey = (FsmKey) o;
            return state.equals(fsmKey.state) && event.equals(fsmKey.event) && version.equals(fsmKey.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, event, version);
        }
    }


}