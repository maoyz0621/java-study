/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.okhttp;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 日志拦截器
 *
 * @author maoyz0621 on 19-8-6
 * @version: v1.0
 */
public class RequestInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    /**
     * @param chain 包含了request和response
     * @return Response
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .build();

        long startTime = System.currentTimeMillis();
        HttpUrl url = request.url();
        LOGGER.info("url = [{}] start process", url);
        String method = request.method();

        // post请求,获取body
        if ("POST".equals(method)) {
            JSONObject jsonObject = new JSONObject();
            RequestBody body = request.body();
            // post 表单形式
            if (body instanceof FormBody) {
                FormBody formBody = (FormBody) body;
                for (int i = 0; i < formBody.size(); i++) {
                    jsonObject.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                LOGGER.info("入参Json = {}", jsonObject.toString());
            } else {
                // post  json格式
                LOGGER.info("{}", body);
            }
        }
        // 放行Request
        Response response = chain.proceed(request);

        /**
         * 这里不能直接使用response.body().string()的方式输出日志
         * 因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一个新的response给应用层处理
         */
        ResponseBody body = response.peekBody(1024 * 1024);

        LOGGER.info("出参 Json = {}", body.string());

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        LOGGER.info("url = {} 访问耗时 {} ms", url, duration);
        return response;
    }
}
