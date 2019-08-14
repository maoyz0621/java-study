/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.okhttp.handle;

import com.myz.java.study.okhttp.OkHttpException;

/**
 * @author maoyz0621 on 19-8-4
 * @version: v1.0
 */
public interface DisposeDataListener<T> {

    /**
     * 请求成功回调事件处理
     */
    void onSuccess(T t);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(OkHttpException ex);

}
