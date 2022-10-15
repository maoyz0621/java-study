/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.profile;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * 并不能读取静态文件，只能加载类似于mysqlURL = ${db.user}/${db.password}的属性值。
 * 基于Spring解析获取 properties 文件单个属性值的方式，
 *
 * @author maoyz0621 on 2022/8/6
 * @version v1.0
 */
public class PropertiesUtils implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }

    public String getPropertiesValue(String name) {
        StringBuilder val = new StringBuilder().append("${").append(name).append("}");
        return stringValueResolver.resolveStringValue(val.toString());
    }
}