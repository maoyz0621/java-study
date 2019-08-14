/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.okhttp.handle;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.myz.java.study.okhttp.OkHttpException;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author maoyz0621 on 19-8-4
 * @version: v1.0
 */
public class CommonJsonCallback<T> implements Callback {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonJsonCallback.class);
    /**
     * errorCode是根据接口返回的标识 实际根据自己接口返回为准
     */
    protected final String RESULT_CODE = "errorCode";
    protected final int RESULT_CODE_VALUE = 0;

    /**
     * errorMsg字段提示信息，实际根据自己接口返回为准
     */
    protected final String ERROR_MSG = "errorMsg";

    protected final String NETWORK_MSG = "请求失败";
    protected final String JSON_MSG = "解析失败";
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;
    protected final int TIMEOUT_ERROR = -4;

    protected final String EMPTY_MSG = "";

    /**
     * 定义专门线程池处理结果
     */
    private ExecutorService threadPool = new ThreadPoolExecutor(
            100,
            150,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_callback_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
    private DisposeDataListener listener;

    private Class<?> clazz;

    T t;

    public CommonJsonCallback(DisposeDataHandle<T> handle) {
        this.listener = handle.getListener();
        this.clazz = handle.getClazz();
    }

    @Override
    public void onFailure(@Nonnull Call call, @Nonnull IOException e) {
        LOGGER.error("请求失败= {} ", e);
        threadPool.execute(() -> {
            if (e instanceof SocketTimeoutException) {
                //判断超时异常
                listener.onFailure(new OkHttpException(TIMEOUT_ERROR, "请求超时"));
            } else if (e instanceof ConnectException) {
                //判断超时异常
                listener.onFailure(new OkHttpException(OTHER_ERROR, "请求服务器失败"));
            } else {
                listener.onFailure(new OkHttpException(NETWORK_ERROR, e.getMessage()));
            }
        });
    }


    @Override
    public void onResponse(@Nonnull Call call, @Nonnull Response response) throws IOException {
        assert response.body() != null;
        final String result = response.body().string();
        threadPool.execute(() -> handleResponse(result));
    }

    private void handleResponse(String body) {
        if (StringUtils.isEmpty(body)) {
            listener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject result = JSONObject.parseObject(body);
            if (result.containsKey(RESULT_CODE)) {
                //todo 从JSON对象中取出我们的响应码，如果为0，则是正确的响应 (实际情况按你们接口文档)
                if (result.getInteger(RESULT_CODE) == RESULT_CODE_VALUE) {
                    //如果class为null  则不解析直接返回json
                    if (clazz == null) {
                        listener.onSuccess(result);
                    } else {
                        // 需要转化为实体对象
                        Object obj = JSONObject.parseObject(body, clazz);
                        if (obj != null) {
                            listener.onSuccess(obj);
                        } else {
                            listener.onFailure(new OkHttpException(JSON_ERROR, JSON_MSG));
                        }
                    }
                } else {
                    //将服务端返回的异常回调到应用层去处理
                    listener.onFailure(new OkHttpException(OTHER_ERROR, result.get(ERROR_MSG) + ""));
                    LOGGER.error("onResponse处理失败");
                }
            }
        } catch (Exception e) {
            listener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            LOGGER.error("onResponse处理失败 {}", e);
        }
    }
}
