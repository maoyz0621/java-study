/**
 * Copyright 2020 Inc.
 **/
package com.myz.buddy.interceptor;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

/**
 * 委托类
 *
 * @author maoyz0621 on 2020/11/23
 * @version v1.0
 */
public class TimeInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
        String name = method.getName();

        if (StringUtils.equalsIgnoreCase(name, "equals") ||
                StringUtils.equalsIgnoreCase(name, "hashCode") || StringUtils.equalsIgnoreCase(name, "toString")) {
            return callable.call();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        long start = System.currentTimeMillis();
        try {
            return callable.call();
        } finally {
            // todo 记录日志
            System.out.println(method.getDeclaringClass().getName() + "." + method.getName() + ": took " + (System.currentTimeMillis() - start) + "ms");

        }
    }

}