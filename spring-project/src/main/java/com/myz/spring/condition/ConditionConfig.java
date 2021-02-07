/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
@Configuration
@ComponentScan("com.myz.spring.condition")
public class ConditionConfig {

    @Bean("conditionBean")
    public ConditionBean conditionBean0() {
        return new ConditionBean();
    }

    @Conditional(FirstCondition.class)
    @Bean("firstConditionBean")
    public ConditionBean conditionBean1() {
        return new ConditionBean();
    }

    @Conditional(SecondCondition.class)
    @Bean("secondConditionBean")
    public ConditionBean conditionBean2() {
        return new ConditionBean();
    }

}
