/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
public class SecondCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}
