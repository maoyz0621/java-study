/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.base;

/**
 * @author maoyz0621 on 2022/7/21
 * @version v1.0
 */
public class SelfAdd {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            count = count++;
        }
        // 0
        System.out.println("count=" + count);

        int count1 = 0;
        for (int i = 0; i < 5; i++) {
            count1 = ++count1;
        }
        // 5
        System.out.println("count1=" + count1);
    }

    /**
     * count = count++;  等同于
     */
    private int add(int i) {
        int temp = i;
        i = i + 1;
        return temp;
    }
}