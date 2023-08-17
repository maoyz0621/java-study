/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author maoyz0621 on 2023/7/25
 * @version v1.0
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RpcClient {

    String value() default "";

    String path() default "";

    long timeout() default 5000L;
}