package com.myz.spring.test;


import com.myz.spring.lazy.LazyBean;
import com.myz.spring.lazy.LazyConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621
 */
public class TestLazySpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestLazySpring.class);

    @Test
    public void testLazy() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);

        LazyBean bean = context.getBean(LazyBean.class);
        LazyBean bean1 = context.getBean(LazyBean.class);
        System.out.println(bean1 == bean);
    }

}
