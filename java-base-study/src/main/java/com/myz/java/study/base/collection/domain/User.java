package com.myz.java.study.base.collection.domain;

/**
 * 　要求数据不能修改
 *
 * @author maoyz on 18-3-1.
 */
public class User {

    private final String name;
    private final int age;

    public User() {
        name = null;
        age = 0;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
