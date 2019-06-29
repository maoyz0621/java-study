package com.myz.spring.test;

import com.myz.spring.service.web.BaseController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {

	/**
	 *
	 */
	@Test
	public void testExecute() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-service.xml");
		BaseController baseController = context.getBean(BaseController.class);
		baseController.execute();
	}

}
