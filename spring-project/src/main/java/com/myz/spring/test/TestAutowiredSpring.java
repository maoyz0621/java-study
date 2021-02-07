package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.autowired.Person;

public class TestAutowiredSpring {


	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-autowired.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}

}
