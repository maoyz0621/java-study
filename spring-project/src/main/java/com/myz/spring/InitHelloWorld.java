package com.myz.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器，在初始化前后进行处理工作
*/
public class InitHelloWorld implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization After:" + bean + ":"+beanName);
		//　对指定的bean处理
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization Before:" + bean + ":"+beanName);
		//　对指定的bean处理
		return bean;
	}

}
