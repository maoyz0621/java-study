/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.aop.code;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.util.Arrays;

/**
 * @author maoyz0621 on 2022/10/20
 * @version v1.0
 */
public class MyAspectJExpressionPointcut {

    public static void main(String[] args) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

        // execution ( 返回类型 方法路径.方法名(参数) )
        pointcut.setExpression("execution(* com.myz.spring.aop.code.A.*a(..))");

        Advice advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                // 方法名称
                String name = invocation.getMethod().getDeclaringClass() + "." + invocation.getMethod().getName();
                // 入参
                Object[] arguments = invocation.getArguments();

                System.out.println(name + " - " + Arrays.toString(arguments));

                System.out.println("============== Before =============");
                Object result = null;
                try {
                    result = invocation.proceed();
                    System.out.println("=============== After ============");
                    return result;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return result;
            }
        };

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        A a = new A();
        proxyFactory.setTarget(a);
        proxyFactory.addAdvisor(advisor);

        A proxy = (A) proxyFactory.getProxy();
        proxy.a(1, 120L);
        proxy.b();
    }


}

class A {

    public A() {
    }

    void a(int a, long l) {
        System.out.println("a");
    }

    void b() {
        System.out.println("b");
    }
}