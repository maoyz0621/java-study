/**
 * Copyright 2020 Inc.
 **/
package com.myz.ajax.pdf;

import lombok.Data;

import java.util.List;

/**
 * @author maoyz0621 on 2020/9/14
 * @version v1.0
 */
@Data
public class TableVo {

    private String tableName;
    private String tableComment;

    private List<ColumnVo> columns;
}