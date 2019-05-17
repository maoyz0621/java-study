package com.myz.java.study.io.bio;

import java.io.Serializable;

/**
 * @author maoyz on 18-3-17.
 */
class Emp implements Serializable{

    private static final long serialVersionUID = -4371188065302708581L;
    /**
     * transient 非序列化关键字
     */
    private /*transient*/ String name;

    private double age;

    public Emp(String name, double age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
