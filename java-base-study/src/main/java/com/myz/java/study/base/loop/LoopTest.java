/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 09:44  Inc. All rights reserved.
 */
package com.myz.java.study.base.loop;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author maoyz
 */
public class LoopTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1", "2", "3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("2")) {
                // break;  // 1
                continue;   // 1 3
            }
            System.out.println(next);
        }
    }
}