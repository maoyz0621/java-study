/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.chain.base.request;

/**
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public class FirstRequestInfo extends AbstractRequestInfo {

    public FirstRequestInfo(String info) {
        super(info);
    }

    /**
     * 返回0
     *
     * @return
     */
    @Override
    public int getCode() {
        return 0;
    }
}
