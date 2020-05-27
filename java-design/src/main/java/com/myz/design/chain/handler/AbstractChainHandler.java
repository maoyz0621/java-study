package com.myz.design.chain.handler;


import com.myz.design.chain.request.AbstractRequestInfo;

import java.util.Objects;

/**
 * 定义抽象类
 *
 * @author maoyz on 18-3-9.
 */
public abstract class AbstractChainHandler {
    /* 传递请求, 自己拥有自己,表示下一个handler */
    private AbstractChainHandler nextChainHandler;

    /* 处理级别 */
    private int level;

    public AbstractChainHandler() {
    }

    public AbstractChainHandler(int level) {
        this.level = level;
    }

    /**
     * 执行目标抽象方法之后，交于链式对象执行,level可修改
     */
    public final void execute(AbstractRequestInfo requestInfo) {
        if (level == requestInfo.getCode()) {
            handlerChainProcess(requestInfo);
        } else {
            // 执行目标方法
            if (nextChainHandler != null) {
                nextChainHandler.execute(requestInfo);
            }
        }
    }

    /**
     * level每个Concrete已经定义好
     */
    public final void handle(AbstractRequestInfo requestInfo) {
        if (getHandlerLevel() == requestInfo.getCode()) {
            handlerChainProcess(requestInfo);
        } else {
            // 执行目标方法
            if (nextChainHandler != null) {
                nextChainHandler.handle(requestInfo);
            }
        }
    }

    /**
     * 此方法也可以通过入参的level进行convent
     */
    protected abstract int getHandlerLevel();

    /**
     * 定义的抽象待执行方法，交于execute()执行
     *
     * @param request
     */
    protected abstract void handlerChainProcess(AbstractRequestInfo request);

    public void setNextChainHandler(AbstractChainHandler nextChainHandler) {
        this.nextChainHandler = nextChainHandler;
    }

}