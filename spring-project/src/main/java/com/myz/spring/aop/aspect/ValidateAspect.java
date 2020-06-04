package com.myz.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 需要配合@Component
 */

@Component
@Aspect
@Order(1)
public class ValidateAspect {

    /**
     * 引入其他类中的切入点
     */
    @Before(value = "com.myz.spring.aop.aspect.LoggingAspect.joinPointMethod()")
    public void beforeValidate() {
        System.out.println("validate begin ..");
    }

    /**
     *
     */
    @After(value = "com.myz.spring.aop.aspect.LoggingAspect.joinPointMethod()")
    public void afterService() {
        System.out.println("validate after ..");
    }

    /**
     * 最后执行
     *
     * @param jp
     * @param retVal
     */
    @AfterReturning(value = "com.myz.spring.aop.aspect.LoggingAspect.joinPointMethod()", returning = "retVal")
    public void afterReturnService(JoinPoint jp, Object retVal) {
        System.out.println("validate after returning .." + jp.getSignature().getName() + retVal);
    }

    /**
     * 最后执行
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "com.myz.spring.aop.aspect.LoggingAspect.joinPointMethod()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        System.out.println("validate after throwing .." + jp.getSignature().getName() + e);
    }

    /**
     * Around优先执行
     *
     * @param pjp
     * @return
     */
    @Around(value = "com.myz.spring.aop.aspect.LoggingAspect.joinPointMethod()")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            System.out.println("validate前置通知...");
            result = pjp.proceed();
            System.out.println("validate返回通知...");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("validate异常通知...");
            throw new RuntimeException(e);
        }
        System.out.println("validate后置通知...");
        return result;
    }


}
