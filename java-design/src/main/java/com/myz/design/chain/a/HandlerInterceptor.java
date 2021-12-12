/**
 * Copyright 2021 Inc.
 **/
package com.myz.design.chain.a;

/**
 *
 * @author maoyz0621 on 2021/12/8
 * @version v1.0
 * @see org.springframework.web.servlet.HandlerInterceptor
 */
public interface HandlerInterceptor {

    /**
     * 返回true时进行
     */
    default boolean preHandler() {
        return true;
    }

    default void postHandler() {
    }

    default void afterCompletion(Exception ex) {
    }
}