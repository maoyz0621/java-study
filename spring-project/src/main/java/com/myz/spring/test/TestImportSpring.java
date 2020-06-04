package com.myz.spring.test;


import com.myz.spring._import.ImportBean;
import com.myz.spring._import.ImportConfig;
import com.myz.spring.lazy.LazyBean;
import com.myz.spring.lazy.LazyConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621
 */
public class TestImportSpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestImportSpring.class);

    @Test
    public void testLazy() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);

        ImportBean bean = context.getBean(ImportBean.class);
        ImportBean bean1 = context.getBean(ImportBean.class);
        System.out.println(bean == bean1);
    }

}
