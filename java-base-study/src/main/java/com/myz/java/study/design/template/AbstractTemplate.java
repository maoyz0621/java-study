/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.template;

/**
 * 模板
 *
 * @author maoyz on 18-10-19
 * @version: v1.0
 */
public abstract class AbstractTemplate {

    /**
     * 模板方法,定义成final,不可更改
     */
    public final void templateMethod() {
        //调用基本方法
        abstractMethod();
        hookMethod();
        concreteMethod();
    }

    /**
     * 基本方法的声明（由子类实现）
     */
    protected abstract void abstractMethod();

    /**
     * 钩子方法,子类可以继承，也可以重写
     */
    protected void hookMethod() {
        System.out.println("AbstractTemplate hookMethod()");
    }


    /**
     * 基本方法（已经实现）
     */
    private final void concreteMethod() {
        System.out.println("AbstractTemplate concreteMethod()");
    }
}
