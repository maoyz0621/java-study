/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.template;

/**
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class TemplateClient {

    public static void main(String[] args) {
        AbstractTemplate templateA = new ConcreteTemplateA();
        templateA.templateMethod();

        System.out.println("-----------------------");

        AbstractTemplate templateB = new ConcreteTemplateB();
        templateB.templateMethod();
    }
}
