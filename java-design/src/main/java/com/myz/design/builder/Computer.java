/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public class Computer {

    private List<String> parts = new ArrayList<String>();

    //用于将组件组装到电脑里
    public void add(String part) {
        parts.add(part);
    }

    public void show() {
        for (int i = 0; i < parts.size(); i++) {
            System.out.println("组件" + parts.get(i) + "装好了");
        }
        System.out.println("电脑组装完成，请验收");
    }
}
