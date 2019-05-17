/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author maoyz0621 on 2019/3/20
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBean {

    private Integer age;

    private int height;

    private Boolean sex;

    private Double salary;

    private Date birth;

    private String addr;
}
