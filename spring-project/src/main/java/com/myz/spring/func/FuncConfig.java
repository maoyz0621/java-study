/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.func;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @author maoyz0621 on 2022/10/15
 * @version v1.0
 */
@ComponentScans(value = @ComponentScan(
        value = "com.myz.spring.func"))
@Configuration
public class FuncConfig {
}