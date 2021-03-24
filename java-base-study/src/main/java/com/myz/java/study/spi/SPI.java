/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-03 16:16  Inc. All rights reserved.
 */
package com.myz.java.study.spi;

import java.lang.annotation.*;

/**
 * @author maoyz
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    String value() default "";
}