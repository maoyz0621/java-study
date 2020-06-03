package com.myz.spring.test;

import com.myz.spring.aop.xml.MyIntroduction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.aop.xml.ICalculator;


public class TestAOPXmlSpring {

	/**
	 *
	 */
	@Test
	public void testAOP() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-aopxml.xml");
		ICalculator iCalculator = (ICalculator) context.getBean("calculator");
		int result = iCalculator.add(2, 5);
		System.out.println(result);
		
		System.out.println("-------------------");
		
		float result1 = iCalculator.divide(4, 0);
		System.out.println(result1);
	}

    /**
     * Introduction
     * 引入,允许我们向现有的类添加新方法属性。把切面（也就是新方法属性：通知定义的）用到目标类中
     */
    @Test
    public void testIntrduction() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aopxml.xml");
        MyIntroduction myIntroduction = (MyIntroduction) context.getBean("calculator");
        myIntroduction.introduction();

    }

}
