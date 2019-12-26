package com.myz.design.chain;

import com.myz.java.study.design.chain.handler.AbstractChainHandler;
import com.myz.java.study.design.chain.request.AbstractRequestInfo;

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
    /**
     * todo 游标
     */
    private int size = 0;

    /**
     * 处理者需要形成环状
     * @param chainHandlers
     */
    public void setChainHandlers(List<AbstractChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
        for (int i = 0; i < chainHandlers.size() - 1; i++) {
            chainHandlers.get(i).setNextChainhandler(chainHandlers.get(i + 1));
        }
    }

    public void setRequestInfo(AbstractRequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    /**
     * 执行目标对象方法,同时移动游标
     */
    public void proceed() {
        // todo 判断游标位置
        if (size >= chainHandlers.size() || size < 0) {
            return;
        }
        chainHandlers.get(0).handlerChainProcess(requestInfo);
    }
}
