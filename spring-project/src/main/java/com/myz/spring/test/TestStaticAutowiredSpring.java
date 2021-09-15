package com.myz.spring.test;

import com.myz.spring._static.UserHelper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621
 */
public class TestStaticAutowiredSpring {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.myz.spring._static");
        UserHelper.get();
    }

}
