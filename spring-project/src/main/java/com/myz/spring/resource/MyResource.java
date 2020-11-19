package com.myz.spring.resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author maoyz on 18-3-6.
 */
public class MyResource implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public void getResource(String path) {
        // 获取路径名称
        Resource resource = context.getResource(path);
        System.out.println(resource.getFilename());
        try {
            System.out.println(resource.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
