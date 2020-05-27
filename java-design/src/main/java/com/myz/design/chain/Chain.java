package com.myz.design.chain;

import com.myz.design.chain.handler.AbstractChainHandler;
import com.myz.design.chain.request.AbstractRequestInfo;

import java.util.LinkedList;
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
    private List<AbstractChainHandler> chainHandlers = new LinkedList<>();
    /**
     * 请求信息
     */
    private AbstractRequestInfo requestInfo;

    public Chain() {
    }

    public Chain(List<AbstractChainHandler> chainHandlers, AbstractRequestInfo requestInfo) {
        this.chainHandlers = chainHandlers;
        this.requestInfo = requestInfo;
    }

    /**
     * 执行目标对象方法,同时移动游标
     */
    public void proceed() {
        chainHandlers.get(0).execute(requestInfo);
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