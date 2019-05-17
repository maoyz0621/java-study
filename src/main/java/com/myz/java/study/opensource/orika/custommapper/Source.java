/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika.custommapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


/**
 * @author maoyz0621 on 19-5-13
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Source {

    private String name;

    private Date date;
}
