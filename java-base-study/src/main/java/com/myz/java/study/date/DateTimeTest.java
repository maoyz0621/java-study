/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.date;

import java.util.Arrays;

/**
 * @author maoyz0621 on 2022/8/15
 * @version v1.0
 */
public class DateTimeTest {

    public static void main(String[] args) {
        String time = "01:10:12";
        String[] split = time.split(":");
        System.out.println(Arrays.toString(split));
        long all = Long.parseLong(split[0]) * 60 * 60 + Long.parseLong(split[1]) * 60 + Long.parseLong(split[2]);
        System.out.println(all);
    }
}