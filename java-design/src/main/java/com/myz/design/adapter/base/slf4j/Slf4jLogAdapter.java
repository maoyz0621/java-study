/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base.slf4j;

import com.myz.design.adapter.base.LogTarget;

/**
 * 适配者角色（Adapter）: 通过包装被适配的对象，将原接口转换为用户所期待的目标接口
 * 实现Target，维护一个指向Adaptee对象引用
 *
 * @author maoyz0621 on 2023/7/11
 * @version v1.0
 */
public class Slf4jLogAdapter implements LogTarget {

    private Slf4jLogAdaptee slf4jLogAdaptee;

    public Slf4jLogAdapter(String clazz) {
        this.slf4jLogAdaptee = new Slf4jLogAdapteeImpl();
    }

    // 依赖Adaptee接口
    public Slf4jLogAdapter(Slf4jLogAdaptee slf4jLogAdaptee) {
        this.slf4jLogAdaptee = slf4jLogAdaptee;
    }

    @Override
    public boolean isDebugEnable() {
        return false;
    }

    @Override
    public void debug(String s) {
        slf4jLogAdaptee.debug(s);
    }
}