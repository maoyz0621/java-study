package com.myz.spring.test;

import com.myz.spring.profile.ProfileBean;
import com.myz.spring.profile.ProfileConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 添加启动参数 -Dspring.profiles.active=test
 * @author maoyz0621
 */
public class TestProfileSpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestProfileSpring.class);

    @Test
    public void testAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
        ProfileBean bean = context.getBean(ProfileBean.class);
        System.out.println(bean);
    }

}
