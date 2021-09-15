package com.myz.spring._static;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/15 19:30
 */
@Component
public class AutowiredStaticSmartInitializingSingleton implements SmartInitializingSingleton {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Autowired
    private AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor;

    @Override
    public void afterSingletonsInstantiated() {
        // beanFactory.autowireBean(new UserHelper());
        autowiredAnnotationBeanPostProcessor.processInjection(new UserHelper());
    }
}
