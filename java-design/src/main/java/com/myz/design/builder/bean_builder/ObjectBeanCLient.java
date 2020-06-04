/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.builder.bean_builder;

/**
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public class ObjectBeanCLient {

    public static void main(String[] args) {
        ObjectBean bean = new ObjectBean.Builder("abc", 12)
                .sex("man")
                .address("anhui")
                .build();
        System.out.println(bean);
    }
}
