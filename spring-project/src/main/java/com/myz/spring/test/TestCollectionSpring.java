package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.collection.Person;

public class TestCollectionSpring {

	/**
	 * @Title: test
	 * @Description: 集合属性
	 * @param:
	 * @return: void
	 * @throws:
	 */
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-collection.xml");
		Person person = (Person) context.getBean("person2");
		System.out.println(person);
	}

}
