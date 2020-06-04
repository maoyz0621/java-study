package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.property.Person;



public class TestPropertySpring {

	/**
	 * @Title: test
	 * @Description: 配置外部属性文件
	 * @param:
	 * @return: void
	 * @throws:
	 */
	@Test
	public void testOneProperty() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-property.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}
	
	@Test
	public void testManyProperties(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-property.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}

}
