/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.template;

/**
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class Client {

    public static void main(String[] args) {
        AbstractTemplate templateA = new ConcreteTemplateA();
        templateA.templateMethod();

        System.out.println("-----------------------");

        AbstractTemplate templateB = new ConcreteTemplateB();
        templateB.templateMethod();
    }
}
