/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @author maoyz0621 on 2023/7/31
 * @version v1.0
 */
@Configuration
@ComponentScans(value = @ComponentScan(
        value = "com.myz.spring.rpc"))
public class RpcAnnotationScanConfig {
}