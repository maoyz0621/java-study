/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonB {
    private String nom;
    private String surnom;
    private int age;
}
