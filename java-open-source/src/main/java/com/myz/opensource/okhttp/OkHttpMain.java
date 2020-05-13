/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.okhttp;


import com.myz.opensource.okhttp.handle.DisposeDataListener;
import com.myz.opensource.okhttp.handle.RequestParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyz0621 on 19-8-7
 * @version: v1.0
 */
public class OkHttpMain {

    public static void main(String[] args) throws IOException {
        Map<String, String> param = new HashMap<>();
        param.put("q", "区块链");
        param.put("page", "1");
        param.put("type", "note");
        String url = "https://www.jianshu.com/search";
        OkHttpServer.get(url, new RequestParams(param));

        OkHttpServer.asyncGet(url, new RequestParams(param), new DisposeDataListener<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println(o);
            }


            @Override
            public void onFailure(OkHttpException ex) {

            }
        }, null);
    }
}
