/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author maoyz0621 on 19-5-15
 * @version: v1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {

    private String isbn;

    private Integer page;
}
