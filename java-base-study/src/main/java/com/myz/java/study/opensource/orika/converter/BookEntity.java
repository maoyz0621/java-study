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
 * @author maoyz0621 on 19-5-14
 * @version: v1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    private String bookName;

    private String authorName;

    private Date authorBirthday;

    // 一个Json字符串
    private String bookInformation;

}
