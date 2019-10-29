/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.design.adapter;

/**
 * @author maoyz0621 on 19-10-24
 * @version: v1.0
 */
public class SimpleController implements Controller {

    @Override
    public void doRequest() {
        System.out.println(getType());
    }

    private String getType() {
        return "Simple";
    }
}
