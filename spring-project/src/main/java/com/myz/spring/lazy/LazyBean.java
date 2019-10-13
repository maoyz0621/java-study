/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 懒加载 @Lazy 只有使用时才加载,IOC容器启动时不会加载, 配合singleton 使用
 * @author maoyz0621 on 19-10-9
 * @version: v1.0
 */
@Lazy
@Component
public class LazyBean {

    public LazyBean() {
        System.out.println("执行LazyBean....");
    }

}
