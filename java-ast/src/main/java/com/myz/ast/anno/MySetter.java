/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-11 17:54  Inc. All rights reserved.
 */
package com.myz.ast.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author maoyz
 */
@Retention(RetentionPolicy.SOURCE)  // 源码中保留
@Target(ElementType.TYPE)   // 使用在类上
public @interface MySetter {
}