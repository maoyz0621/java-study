/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Name {

    private String firstName;

    private String lastName;

    private Date birth;

}
