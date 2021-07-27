/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.chain.base.request;

/**
 * 待处理请求信息
 *
 * @author maoyz on 2018/7/23
 * @version: v1.0
 */
public abstract class AbstractRequestInfo {

    private String info;

    public AbstractRequestInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 需要覆写办法
     *
     * @return
     */
    public abstract int getCode();
}
