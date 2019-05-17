package com.myz.java.study.design.chain.handler;

import com.myz.java.study.design.chain.request.AbstractRequestInfo;

import java.util.Objects;

/**
 * @author maoyz on 18-3-9.
 */
public abstract class AbstractChainHandler implements Handler {
    /**
     * 传递请求
     */
    private AbstractChainHandler nextChainhandler;

    /**
     * 执行目标抽象方法之后，交于链式对象执行
     */
    public final void execute(AbstractRequestInfo requestInfo) {
        // 执行目标方法
        if (!Objects.isNull(nextChainhandler)) {
            nextChainhandler.handlerChainProcess(requestInfo);
        }
    }

    public abstract int getHandlerCode();

    public void setNextChainhandler(AbstractChainHandler nextChainhandler) {
        this.nextChainhandler = nextChainhandler;
    }

}
