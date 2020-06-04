/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.builder.bean_builder;

/**
 * @author maoyz on 18-10-23
 * @version: v1.0
 */
public class ObjectBean {

    private String name;
    private int age;
    private String sex;
    private String address;

    private ObjectBean(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.address = builder.address;
    }

    /**
     * 创建者
     */
    public static class Builder {
        // required params
        private final String name;
        private final int age;

        // optional params
        private String sex;
        private String address;

        public Builder() {
            // 默认值
            this.name = "mao";
            this.age = 12;
        }

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder sex(String val) {
            sex = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        /**
         * 提供创建对象的方法
         */
        public ObjectBean build() {
            return new ObjectBean(this);
        }
    }

    @Override
    public String toString() {
        return "ObjectBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
