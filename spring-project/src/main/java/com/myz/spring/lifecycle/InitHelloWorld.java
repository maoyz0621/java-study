package com.myz.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
*/
public class InitHelloWorld implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("After:" + beanName);
		//　对指定的bean处理
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Before:" + beanName);
		//　对指定的bean处理
		return bean;
	}

	public void say(){
		System.out.println("执行say()");
	}


}
