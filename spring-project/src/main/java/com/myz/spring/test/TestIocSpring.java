package com.myz.spring.test;

import com.myz.spring.ioc.IocA;
import com.myz.spring.ioc.IocB;
import com.myz.spring.ioc.circularReference.constructor.ConstructorIocA;
import com.myz.spring.ioc.circularReference.constructor.ConstructorIocB;
import com.myz.spring.ioc.circularReference.prototype.PrototypeIocA;
import com.myz.spring.ioc.circularReference.prototype.PrototypeIocB;
import com.myz.spring.ioc.circularReference.setter.SetterIocA;
import com.myz.spring.ioc.circularReference.setter.SetterIocB;
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

	@Test
	public void testCircularReferencePrototype() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-ioc.xml");
		context.getBean(PrototypeIocA.class);
		context.getBean(PrototypeIocA.class);
		context.getBean(PrototypeIocB.class);
		context.getBean(PrototypeIocB.class);
	}

	@Test
	public void testCircularReferenceSetter() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-ioc.xml");
		context.getBean(SetterIocA.class);
		context.getBean(SetterIocB.class);
		context.getBean(SetterIocA.class);
		context.getBean(SetterIocB.class);
	}

	@Test
	public void testCircularReferenceConstructor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-ioc.xml");
		context.getBean(ConstructorIocA.class);
		context.getBean(ConstructorIocB.class);
		context.getBean(ConstructorIocA.class);
		context.getBean(ConstructorIocB.class);
	}
	
}
