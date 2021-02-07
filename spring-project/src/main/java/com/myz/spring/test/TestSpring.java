package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.HelloWorld;
import com.myz.spring.Person;
import com.myz.spring.School;

public class TestSpring {

	/**
	 *
	 */
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorld helloWorld = (HelloWorld) context.getBean("helloworld");
		System.out.println(helloWorld);
	}

	/**
	 * 执行destory()方法
	 * 使用AbstractApplicationContext，执行registerShutdownHook()
	 */
	@Test
	public void testDestory() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorld helloWorld = (HelloWorld) context.getBean("helloworld");
		System.out.println(helloWorld);
		context.close();
		context.registerShutdownHook();
	}
	
	@Test
	public void testRef() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
		
		School school = (School) context.getBean("school");
		System.out.println(school);
	}

}
