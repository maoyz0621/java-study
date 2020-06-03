package com.myz.spring.test;

import com.myz.spring.resource.MyResource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * Resource测试
 */
public class TestResourceSpring {

    @Test
    public void testResource() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-resource.xml");
        MyResource myResource = (MyResource) context.getBean("myResource");
        // classpath 本地路径
        myResource.getResource("bean.txt");
        // file
        myResource.getResource("file:/home/maoyz/文档/JAVAWeb/SpringProject/src/main/resources/bean.txt");
        // url
        myResource.getResource("http://blog.csdn.net/xiangjai/article/details/53954252");


        // 获取国际化信息 MessageSource
        MessageSource message = (MessageSource) context.getBean("messageSource");
        String username1 = message.getMessage("username", new Object[]{}, Locale.CHINA);
        System.out.println("中文信息:" + username1);

        String username2 = message.getMessage("username", new Object[]{}, Locale.ENGLISH);
        System.out.println("英文信息:" + username2);
    }

}
