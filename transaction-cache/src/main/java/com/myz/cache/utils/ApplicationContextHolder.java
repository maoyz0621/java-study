package com.myz.cache.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 提供获取ApplicationContext
 *
 * @author maoyz0621
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    protected static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

    private static ApplicationContext applicationContext;

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("ApplicationContextHolder start setApplicationContext()");

        ApplicationContextHolder.applicationContext = applicationContext;
    }
}
