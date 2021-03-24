/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-07 10:47  Inc. All rights reserved.
 */
package com.myz.design.adapter;

/**
 * @author maoyz
 */
public abstract class AbstractController implements Controller {

    /**
     * 执行流程，禁止子类重写
     */
    @Override
    public final void doRequest() {
        hookMethod();
        concreteMethod();
        System.out.println(abstractMethod());
    }


    protected void hookMethod() {
        System.out.println("hookMethod");
    }

    protected final void concreteMethod() {
        System.out.println("concreteMethod");
    }

    public abstract String abstractMethod();
}