/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.okhttp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 19-8-4
 * @version: v1.0
 */
public class OkHttpManager {

    private static OkHttpClient okHttpClient;

    private static final Long TIME_OUT = 30L;

    private OkHttpManager() {
        initClient();
    }

    private static class OkHttpManagerHolder {
        private static final OkHttpManager INSTANCE = new OkHttpManager();
    }

    public static OkHttpManager getInstance() {
        return OkHttpManagerHolder.INSTANCE;
    }

    /**
     * 初始化客户端
     *
     * @return OkHttpClient
     */
    private void initClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        //设置重定向
        okHttpClientBuilder.followRedirects(true);

        /*--添加请求头 --*/
        okHttpClientBuilder.addInterceptor(new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        // .addHeader("User-Agent", "Android—Mobile")
                        .build();
                return chain.proceed(request);
            }
        });

        // 添加https支持
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        okHttpClientBuilder.addInterceptor(new RequestInterceptor());

        okHttpClient = okHttpClientBuilder.build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


}
