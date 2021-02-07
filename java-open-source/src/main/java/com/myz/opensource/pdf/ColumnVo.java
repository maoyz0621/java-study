/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.pdf;

import lombok.Data;

/**
 * @author maoyz0621 on 2020/9/14
 * @version v1.0
 */
@Data
public class ColumnVo {
    private String columnName;

    private String columnType;
    private String columnKey;
    private String isNullable;
    private String columnComment;

}