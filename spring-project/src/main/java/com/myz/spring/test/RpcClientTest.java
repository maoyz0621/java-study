/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.test;

import com.myz.spring.rpc.Aservice;
import com.myz.spring.rpc.RpcAnnotationScanConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621 on 2023/7/25
 * @version v1.0
 */
public class RpcClientTest {

    @Test
    public void testRpcClientRegistry() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RpcAnnotationScanConfig.class);
        Aservice bean = context.getBean(Aservice.class);
        bean.a("a");
        bean.b("abc");
    }
}