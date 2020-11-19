/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.chain.handler;

import com.myz.design.chain.request.AbstractRequestInfo;

/**
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public class FirstHandler extends AbstractChainHandler {
    public FirstHandler() {
    }

    public FirstHandler(int level) {
        super(level);
    }

    @Override
    public void handlerChainProcess(AbstractRequestInfo requestInfo) {
        System.out.println("FirstHandler " + requestInfo.getInfo());
    }

    @Override
    public int getHandlerLevel() {
        return 0;
    }
}
