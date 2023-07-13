/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base.slf4j;

/**
 * 需要被适配的类
 * 定义真正执行的业务逻辑，但其接口不被使用者直接使用，不实现Target接口
 *
 * @author maoyz0621 on 2023/7/11
 * @version v1.0
 */
public interface Slf4jLogAdaptee {

    void debug(String s);
}