/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.template;

/**
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class ConcreteTemplateA extends AbstractTemplate {

    @Override
    protected void abstractMethod() {
        System.out.println("ConcreteTemplateA abstractMethod()");
    }
}
