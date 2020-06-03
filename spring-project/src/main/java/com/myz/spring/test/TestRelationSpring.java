package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.autowired.Person;



public class TestRelationSpring {

	/**
	 * @Title: test
	 * @Description: 继承
	 * @param:
	 * @return: void
	 * @throws:
	 */
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-relation.xml");
		Person person = (Person) context.getBean("person1");
		System.out.println(person);
		
/*		Person person1 = (Person) context.getBean("person2");
		System.out.println(person1);*/
	}

}
