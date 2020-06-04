package com.myz.spring.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

/**
 * 使用@Configuration和@Bean配置文件, @EnableAspectJAutoProxy配置AspectJ
 *
 * @author maoyz on 18-3-6.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    @Bean(name = "myBean", initMethod = "init", destroyMethod = "destory")
    // @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MyBean getBean() {
        return new MyBeanImp();
    }
}
