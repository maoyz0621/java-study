/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base;

/**
 * 目标角色（Target）：使用者能够直接使用的接口。以处理遗留代码为例，Target 就是最新定义的业务接口。
 *
 * @author maoyz0621 on 2023/7/11
 * @version v1.0
 */
public interface LogTarget {

    boolean isDebugEnable();

    void debug(String s);
}