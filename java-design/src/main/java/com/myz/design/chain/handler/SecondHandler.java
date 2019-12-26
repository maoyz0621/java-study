/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.chain.handler;

import com.myz.java.study.design.chain.request.AbstractRequestInfo;

import java.util.Objects;

/**
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public class SecondHandler extends AbstractChainHandler {

    @Override
    public void handlerChainProcess(AbstractRequestInfo requestInfo) {
        if (Objects.equals("second", requestInfo.getInfo())) {
            System.out.println(requestInfo.getInfo());
        } else {
            super.execute(requestInfo);
        }
    }

    @Override
    public int getHandlerCode() {
        return 1;
    }
}
