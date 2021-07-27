/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.chain.base.request;

/**
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public class SecondRequestInfo extends AbstractRequestInfo {

    public SecondRequestInfo(String info) {
        super(info);
    }

    @Override
    public int getCode() {
        return 1;
    }

}
