/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.guava.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author maoyz0621 on 2022/1/9
 * @version v1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String name;

    private double height;
}