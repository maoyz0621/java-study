/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentBo {

    private String name;

    private Integer age;

    private Date birth;
}
