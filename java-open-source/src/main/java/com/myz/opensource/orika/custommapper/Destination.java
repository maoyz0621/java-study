/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.custommapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author maoyz0621 on 19-5-13
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Destination {

    private String name;

    private long timeStamp;
}
