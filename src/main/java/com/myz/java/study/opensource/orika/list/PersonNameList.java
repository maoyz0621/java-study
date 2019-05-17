/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonNameList {

    private List<String> nameLists;
}
