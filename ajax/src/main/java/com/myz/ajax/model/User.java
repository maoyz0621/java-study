/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.ajax.model;

import java.io.Serializable;

/**
 * @author maoyz on 18-11-15
 * @version: v1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1698843822694267813L;

    private String username;
    private Integer age;

    public User() {
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
