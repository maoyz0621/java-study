/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.design.adapter;

/**
 * @author maoyz0621 on 19-10-23
 * @version: v1.0
 */
public class AdapterClient {

    public static void main(String[] args) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setController(new SimpleController());
        dispatcherServlet.doDispatch();

        System.out.println("\r\n==============================\r\n");

        dispatcherServlet.setController(new AnnotationController());
        dispatcherServlet.doDispatch();
    }
}
