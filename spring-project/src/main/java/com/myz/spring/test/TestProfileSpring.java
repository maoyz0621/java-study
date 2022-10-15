package com.myz.spring.test;

import com.myz.spring.profile.ProfileBean;
import com.myz.spring.profile.ProfileConfig;
import com.myz.spring.profile.PropertiesUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 添加启动参数 -Dspring.profiles.active=test
 *
 * @author maoyz0621
 */
public class TestProfileSpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestProfileSpring.class);

    @Test
    public void testAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
        PropertiesUtils bean = context.getBean(PropertiesUtils.class);
        System.out.println(bean);
        String propertiesValue = bean.getPropertiesValue("db.name");
        LOGGER.info("={}", propertiesValue);
    }

    @Test
    public void testProfile() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfig.class);
        context.refresh();
        ProfileBean bean = context.getBean(ProfileBean.class);
        System.out.println(bean);
        bean.test();
    }

    /**
     * org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No qualifying bean of type 'com.myz.spring.profile.ProfileBean' available
     */
    @Test
    public void testProfileError() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
        context.getEnvironment().setActiveProfiles("dev");
        ProfileBean bean = context.getBean(ProfileBean.class);
        System.out.println(bean);
        bean.test();
    }
}
