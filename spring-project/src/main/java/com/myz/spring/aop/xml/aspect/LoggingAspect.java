package com.myz.spring.aop.xml.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * 1 将此类声明为切面
 * 2 同时这个类必须是IOC容器
 * 3 执行优先级@Order
 * 4 ProceedJOinPoint  proceed()环绕通知
 * @author xuwt
 */

public class LoggingAspect {
	
	public void joinPointMethod() {
	}

	public void beforeService(JoinPoint joinPoint) {
		//　方法名
		String methodName = joinPoint.getSignature().getName();
		//　传入值
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("begin .." + methodName + ":" + args);
	}

	public void afterService() {
		System.out.println("after ..");
	}

	public void afterReturnService(JoinPoint jp, Object retVal) {
		System.out.println("after returning .." + jp.getSignature().getName() + retVal);
	}

	public void afterThrowing(JoinPoint jp, Exception e) {
		System.out.println("after throwing .." + jp.getSignature().getName());
	}
	
	public Object around(ProceedingJoinPoint pjp){
        //
		Object result = null ;

		// 执行
		try {
			System.out.println("前置通知:");
			result = pjp.proceed();
			System.out.println("返回通知：");
		} catch (Throwable e) {
			System.out.println("异常通知：");
			throw new RuntimeException(e);
		}

		System.out.println("后置通知");
		return result;
	}

}
