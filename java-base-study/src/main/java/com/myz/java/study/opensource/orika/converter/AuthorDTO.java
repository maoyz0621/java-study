/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author maoyz0621 on 19-5-15
 * @version: v1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private String name;

    private Date birthday;
}
