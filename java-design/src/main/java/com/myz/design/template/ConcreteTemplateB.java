/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.template;

/**
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class ConcreteTemplateB extends AbstractTemplate {
    @Override
    protected void abstractMethod() {
        System.out.println("ConcreteTemplateB abstractMethod()");
    }

    @Override
    protected void hookMethod() {
        super.hookMethod();
        System.out.println("ConcreteTemplateB hookMethod()");
    }
}
