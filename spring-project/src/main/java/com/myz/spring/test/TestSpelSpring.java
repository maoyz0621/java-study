package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.spel.Person;

public class TestSpelSpring {

	/**
	 * @Title: test
	 * @Description: SpEL表达式
	 * @param:
	 * @return: void
	 * @throws:
	 */
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-spel.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}

}
