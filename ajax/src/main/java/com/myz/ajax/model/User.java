/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.ajax.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author maoyz on 18-11-15
 * @version: v1.0
 */
@Data
@ToString
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

}
