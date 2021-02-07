package com.myz.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * proxyTargetClass=true  true使用cglib,false使用java的Proxy
 * exposeProxy=true  表示通过aop框架暴露该代理对象，aopContext能够访问.
 *
 * @author maoyz0621
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TransactionCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionCacheApplication.class, args);
    }
}
