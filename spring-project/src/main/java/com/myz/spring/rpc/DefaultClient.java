/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

/**
 * @author maoyz0621 on 2023/7/25
 * @version v1.0
 */
public class DefaultClient {

    private String value;

    private String path;

    private long timeout;

    public DefaultClient() {
    }

    public DefaultClient(String value, String path, long timeout) {
        this.value = value;
        this.path = path;
        this.timeout = timeout;
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