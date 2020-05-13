/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.okhttp;

import com.myz.opensource.okhttp.handle.CommonJsonCallback;
import com.myz.opensource.okhttp.handle.DisposeDataHandle;
import com.myz.opensource.okhttp.handle.DisposeDataListener;
import com.myz.opensource.okhttp.handle.RequestParams;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maoyz0621 on 19-8-5
 * @version: v1.0
 */
public class OkHttpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpServer.class);
    /**
     * 文件上传请求
     *
     * @return
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String get(String url, RequestParams params, Map<String, String> headers) {
        Call call = sendRequest(createGetRequest(url, headers, params));
        Response response;
        try {
            response = call.execute();
            LOGGER.info("Response = {}", response);
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            LOGGER.error("发送同步-get请求发生异常：url = {} ", e.fillInStackTrace());
        }
        return null;
    }

    public static String get(String url, RequestParams params) {
        return get(url, params, null);
    }

    public static String get(String url) {
        return get(url, null);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        return post(url, new RequestParams(params), headers);
    }

    public static String post(String url, RequestParams params, Map<String, String> headers) {
        Call call = sendRequest(createPostRequest(url, params, headers));
        Response response;
        try {
            response = call.execute();
            LOGGER.info("Response = {}", response);
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            LOGGER.error("发送同步-post请求发生异常：url = {} ", e.fillInStackTrace());
        }
        return null;
    }

    public static String post(String url, Map params) {
        return post(url, params, null);
    }

    public static String post(String url, RequestParams params) {
        return post(url, params, null);
    }

    public static String postJson(String url, String json, Map<String, String> headers) {
        Call call = sendRequest(createPostJsonRequest(url, json, headers));
        Response response;
        try {
            response = call.execute();
            LOGGER.info("Response = {}", response);
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            LOGGER.error("发送同步-postJson请求发生异常：url = {} ", e.fillInStackTrace());
        }
        return null;
    }

    public static String postJson(String url, Map<String, String> params, Map<String, String> headers) {
        return postJson(url, new RequestParams(params), headers);
    }

    public static String postJson(String url, RequestParams params, Map<String, String> headers) {
        return postJson(url, com.alibaba.fastjson.JSON.toJSONString(params), headers);
    }

    public static String postJson(String url, String json) {
        return postJson(url, json, null);
    }

    public static String postJson(String url, Map<String, String> params) {
        return postJson(url, params, null);
    }

    public static String postJson(String url, RequestParams params) {
        return postJson(url, params, null);
    }

    public static void asyncGet(String url, RequestParams params, Map<String, String> headers, DisposeDataListener<?> listener, Class<?> clazz) throws IOException {
        asyncSendRequest(createGetRequest(url, headers, params), new CommonJsonCallback(new DisposeDataHandle(listener, clazz)));
    }

    public static void asyncGet(String url, RequestParams params, DisposeDataListener<?> listener, Class<?> clazz) throws IOException {
        asyncGet(url, params, null, listener, clazz);
    }

    public static void asyncGet(String url, DisposeDataListener<?> listener, Class<?> clazz) throws IOException {
        asyncGet(url, null, listener, clazz);
    }

    public static void asyncPost(String url, RequestParams params, Map<String, String> headers, DisposeDataListener listener, Class<?> clazz) {
        Call call = sendRequest(createPostRequest(url, params, headers),
                new CommonJsonCallback(new DisposeDataHandle(listener, clazz)));
    }

    public static void asyncPost(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        asyncPost(url, params, null, listener, clazz);
    }

    public static void asyncPostJson(String url, RequestParams params, Map<String, String> headers, DisposeDataListener listener, Class<?> clazz) {
        Call call = sendRequest(createPostJsonRequest(url, params, headers),
                new CommonJsonCallback(new DisposeDataHandle(listener, clazz)));
    }

    public static void asyncPostJson(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        asyncPostJson(url, params, null, listener, clazz);
    }

    /**
     * @param url
     * @param headerParam
     * @param requestParams
     * @return 返回get的Request对象
     */
    private static Request createGetRequest(String url, Map<String, String> headerParam, RequestParams requestParams) {
        Request.Builder builder = buildHeader(headerParam);
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (requestParams != null && requestParams.hasParams()) {
            ConcurrentHashMap<String, String> params = requestParams.getUrlParams();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                // 将请求参数逐一添加到请求体中
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            builder.url(urlBuilder.substring(0, urlBuilder.length() - 1));
        }
        return builder.get().build();
    }


    /**
     * @param url
     * @param params
     * @return 返回post的Request对象
     */
    private static Request createPostRequest(String url, RequestParams params, Map<String, String> headerParam) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                // 将请求参数逐一添加到请求体中
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        Request.Builder request = buildHeader(headerParam);
        FormBody formBody = formBuilder.build();
        return request
                .url(url)
                .post(formBody)
                .build();
    }

    private static Request.Builder buildHeader(Map<String, String> headerParam) {
        Request.Builder request = new Request.Builder();
        if (headerParam != null) {
            Headers headers = Headers.of(headerParam);
            request.headers(headers);
        }
        return request;
    }

    /**
     * @param url
     * @param params
     * @return 返回postJson的Request对象
     */
    private static Request createPostJsonRequest(String url, RequestParams params, Map<String, String> headerParam) {
        return createPostJsonRequest(url, com.alibaba.fastjson.JSON.toJSONString(params), headerParam);
    }

    private static Request createPostJsonRequest(String url, String params, Map<String, String> headerParam) {
        Request.Builder request = buildHeader(headerParam);
        RequestBody body = RequestBody.create(JSON, params);
        return request
                .url(url)
                .post(body)
                .build();
    }


    private static Request createMultiPostRequest(String url, RequestParams params) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.getFileParams().entrySet()) {
                if (entry.getValue() instanceof File) {
                    requestBody.addPart(okhttp3.Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {

                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return new Request.Builder()
                .url(url)
                .post(requestBody.build())
                .build();
    }

    /**
     * 发送具体的http/https的请求
     *
     * @param request 请求
     * @return Call
     */
    private static Call sendRequest(final OkHttpClient okHttpClient, Request request) {
        return okHttpClient.newCall(request);
    }

    private static Call sendRequest(Request request) {
        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();
        return sendRequest(okHttpClient, request);
    }

    private static void asyncSendRequest(final OkHttpClient okHttpClient, Request request, CommonJsonCallback commonCallback) throws IOException {
        Call call = okHttpClient.newCall(request);
        call.enqueue(commonCallback);
    }

    /**
     * 异步
     *
     * @param request
     * @param commonCallback
     * @return
     * @throws IOException
     */
    private static void asyncSendRequest(Request request, CommonJsonCallback commonCallback) throws IOException {
        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();
        asyncSendRequest(okHttpClient, request, commonCallback);
    }

    public static Call sendRequest(Request request, Callback commonCallback) {
        Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
        call.enqueue(commonCallback);
        return call;
    }
}
