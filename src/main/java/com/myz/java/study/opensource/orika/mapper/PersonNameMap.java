/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.orika.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonNameMap {

    private Map<String, String> nameMap;
}
