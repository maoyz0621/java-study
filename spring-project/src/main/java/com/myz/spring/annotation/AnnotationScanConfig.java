/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.annotation;

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
        value = "com.myz.spring.annotation",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilterType.class)},
        useDefaultFilters = false))
public class AnnotationScanConfig {
}
