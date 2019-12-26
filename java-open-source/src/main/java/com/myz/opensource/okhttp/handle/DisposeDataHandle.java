/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.okhttp.handle;

/**
 * @author maoyz0621 on 19-8-4
 * @version: v1.0
 */
public class DisposeDataHandle<T> {

    private DisposeDataListener<T> listener;

    private Class<?> clazz;

    private String source;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.listener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.listener = listener;
        this.clazz = clazz;
    }

    public DisposeDataHandle(DisposeDataListener listener, String source) {
        this.listener = listener;
        this.source = source;
    }

    public DisposeDataListener getListener() {
        return listener;
    }

    public void setListener(DisposeDataListener listener) {
        this.listener = listener;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
