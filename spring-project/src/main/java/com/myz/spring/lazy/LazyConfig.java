/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.lazy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author maoyz0621 on 19-9-28
 * @version: v1.0
 */

@Configuration
@ComponentScans(value = @ComponentScan(
        value = "com.myz.spring.lazy",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION)},
        useDefaultFilters = true))
public class LazyConfig {
}
