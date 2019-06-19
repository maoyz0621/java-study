/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.chain.handler;

import com.myz.java.study.design.chain.request.AbstractRequestInfo;

/**
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public interface Handler {

    /**
     * 定义的抽象待执行方法，交于execute()执行
     *
     * @param request
     */
    void handlerChainProcess(AbstractRequestInfo request);
}
