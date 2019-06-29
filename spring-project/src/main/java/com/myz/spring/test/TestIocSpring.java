package com.myz.spring.test;

import com.myz.spring.ioc.IocA;
import com.myz.spring.ioc.IocB;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestIocSpring {

	/**
	 * 构造方法存在循环依赖
	 * 1、setter注入方式
	 * 2、@Lazy注解
	 */
	@Test
	public void testAOP() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-ioc.xml");
		context.getBean(IocA.class);
		context.getBean(IocA.class);
		context.getBean(IocB.class);
		context.getBean(IocB.class);
	}
	
}
