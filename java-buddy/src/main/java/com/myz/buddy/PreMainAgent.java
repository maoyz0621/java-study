/**
 * Copyright 2020 Inc.
 **/
package com.myz.buddy;


import com.myz.buddy.interceptor.TimeInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * 定义premain方法
 * @author maoyz0621 on 2020/11/23
 * @version v1.0
 */
public class PreMainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {

            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return builder
                        .method(ElementMatchers.<MethodDescription>any())  // 方法拦截
                        .intercept(MethodDelegation.to(TimeInterceptor.class));  // 授权拦截器
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {

            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        };

        String projectName = agentArgs;
        // 构建builder
        new AgentBuilder.Default()
                .type(ElementMatchers.<TypeDescription>nameStartsWith(projectName)
                        .and(ElementMatchers.not(ElementMatchers.isAnnotation()))  // 排除注解
                        .and(ElementMatchers.not(ElementMatchers.isInterface()))  // 排除接口
                        .and(ElementMatchers.not(ElementMatchers.isEnum()))  // 排除枚举
                ) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }
}