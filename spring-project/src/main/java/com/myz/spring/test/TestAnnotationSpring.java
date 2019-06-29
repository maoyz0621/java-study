package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.annotation.Person;


public class TestAnnotationSpring {

	@Test
	public void testAOP() {
	
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-annotation.xml");
		Person p = (Person) context.getBean("person");
		p.eat();
		System.out.println(p);

	}
	
}
