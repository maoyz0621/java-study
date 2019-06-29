package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.factory.Dog;

public class TestFactorySpring {

	/**
	 * @Title: test
	 * @Description: 测试静态工厂方法
	 * @param:
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testStatic() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-factory.xml");
		Dog dog = (Dog) context.getBean("dog1");
		System.out.println(dog);
	}
	
	@Test
	public void testInstance(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-factory.xml");
		Dog dog = (Dog) context.getBean("dog2");
		System.out.println(dog);
	}
}
