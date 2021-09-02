/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author maoyz0621 on 2020/9/27
 * @version v1.0
 */
@Setter
@Getter
public class Table implements Serializable {

    private String index;

    private Integer column;
}