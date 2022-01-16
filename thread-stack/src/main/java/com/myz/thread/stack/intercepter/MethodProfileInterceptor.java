package com.myz.thread.stack.intercepter;

import com.myz.thread.stack.profile.ThreadProfile;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/27 14:39
 */
public class MethodProfileInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String className = invocation.getMethod().getDeclaringClass().getName();
        String methodName = invocation.getMethod().getName();

        // 性能统计，记录堆栈
        ThreadProfile.enter(className, methodName);
        Object result = null;
        try {
            result = invocation.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            // 性能统计，记录堆栈，退出
            ThreadProfile.exit();
        }
        return result;
    }
}
