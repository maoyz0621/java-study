/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.ajax.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回查询结果集
 *
 * @author maoyz on 18-11-13
 * @version: v1.0
 */
@Data
@ToString
public class QueryResult<T> implements Serializable {

    private static final long serialVersionUID = -6539447684692317035L;

    private ResultCodeEnum code;

    private String message;

    private T data;

    private Integer page;

}
