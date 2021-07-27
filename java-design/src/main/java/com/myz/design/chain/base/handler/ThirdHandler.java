/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.chain.base.handler;


import com.myz.design.chain.base.request.AbstractRequestInfo;

/**
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public class ThirdHandler extends AbstractChainHandler {

    public ThirdHandler() {
    }

    public ThirdHandler(int level) {
        super(level);
    }

    @Override
    public void handlerChainProcess(AbstractRequestInfo requestInfo) {
        System.out.println("ThirdHandler " + requestInfo.getInfo());
    }

    @Override
    public int getHandlerLevel() {
        return 2;
    }
}
