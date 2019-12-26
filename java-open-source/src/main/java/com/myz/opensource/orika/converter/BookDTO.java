/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author maoyz0621 on 19-5-14
 * @version: v1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String bookName;

    // 有两个属性 name 和 birthday
    private AuthorDTO author;

    // 一个类包含 isbn 和 page
    private BookInfo bookInfo;

}
