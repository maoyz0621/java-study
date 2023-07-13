package com.myz.design.chain.base;

import com.myz.design.chain.base.handler.AbstractChainHandler;
import com.myz.design.chain.base.request.AbstractRequestInfo;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式
 * 整合了处理器和请求
 *
 * @author maoyz on 18-3-9.
 */
public class Chain {

    /**
     * 处理器
     */
    private List<AbstractChainHandler> chainHandlers;
    /**
     * 请求信息
     */
    private AbstractRequestInfo requestInfo;

    public Chain(List<AbstractChainHandler> chainHandlers) {
        Assert.notNull(chainHandlers);
        this.chainHandlers = new ArrayList<>();
        this.chainHandlers.addAll(chainHandlers);
        setChainHandlers(this.chainHandlers);
    }

    public Chain(AbstractChainHandler... chainHandlers) {
        this.chainHandlers = new ArrayList<>();
        CollectionUtils.mergeArrayIntoCollection(chainHandlers, this.chainHandlers);
        setChainHandlers(this.chainHandlers);
    }

    public Chain(List<AbstractChainHandler> chainHandlers, AbstractRequestInfo requestInfo) {
        this(chainHandlers);
        this.requestInfo = requestInfo;
    }

    /**
     * 执行目标对象方法,同时移动游标
     */
    public void proceed() {
        if (CollectionUtils.isEmpty(chainHandlers)) {
            return;
        }
        chainHandlers.get(0).execute(requestInfo);
    }

    public void handle() {
        if (CollectionUtils.isEmpty(chainHandlers)) {
            return;
        }
        chainHandlers.get(0).handle(requestInfo);
    }

    public void proceedChain() {
        for (AbstractChainHandler chainHandler : chainHandlers) {
            chainHandler.proceed(requestInfo);
        }
    }

    /**
     * 处理者需要形成环状
     *
     * @param chainHandlers
     */
    public void setChainHandlers(List<AbstractChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
        for (int i = 0; i < chainHandlers.size() - 1; i++) {
            chainHandlers.get(i).setNextChainHandler(chainHandlers.get(i + 1));
        }
    }

    public void setRequestInfo(AbstractRequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}