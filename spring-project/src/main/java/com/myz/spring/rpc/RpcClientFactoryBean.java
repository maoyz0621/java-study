/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author maoyz0621 on 2023/7/25
 * @version v1.0
 */
public class RpcClientFactoryBean<T> implements FactoryBean<DefaultClient> {

    private Class<T> feignInterface;

    private String value;

    private String path;

    private long timeout;

    public RpcClientFactoryBean(Class<T> feignInterface) {
        this.feignInterface = feignInterface;
    }

    @Override
    public DefaultClient getObject() throws Exception {
        return new DefaultClient(value, path, timeout);
    }

    @Override
    public Class<T> getObjectType() {
        return this.feignInterface;
    }

    public Class<T> getFeignInterface() {
        return feignInterface;
    }

    public void setFeignInterface(Class<T> feignInterface) {
        this.feignInterface = feignInterface;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}