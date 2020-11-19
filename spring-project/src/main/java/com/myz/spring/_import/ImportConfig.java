/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring._import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 导入类 @Import
 * ImportSelector
 * ImportBeanDefinitionRegistrar
 * @author maoyz0621 on 19-9-29
 * @version: v1.0
 */
@Configuration
@Import({ImportBean.class})
public class ImportConfig {
}
