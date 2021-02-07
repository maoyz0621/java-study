/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.aop.aspect_no_xml;

import com.myz.spring.aop.annotation.Calculator;
import com.myz.spring.aop.annotation.CalculatorImp;
import com.myz.spring.aop.annotation.ICalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 开启aop 注解  @EnableAspectJAutoProxy
 *
 * AspectJAutoProxyRegistrar
 *   AnnotationAwareAspectJAutoProxyCreator
 *
 * @author maoyz0621 on 19-10-11
 * @version: v1.0
 */
@EnableAspectJAutoProxy
@Configuration
public class AspectConfig {

    @Bean
    public ICalculator calculator() {
        return new CalculatorImp();
    }

    @Bean
    public Calculator calculator1() {
        return new Calculator();
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public ValidateAspect validateAspect() {
        return new ValidateAspect();
    }
}
