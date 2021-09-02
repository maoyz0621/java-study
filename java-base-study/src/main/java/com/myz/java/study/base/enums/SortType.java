package com.myz.java.study.base.enums;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/8/4 14:31
 */
public enum SortType {
    ASC,

    DESC;


    public static void main(String[] args) {
        System.out.println(SortType.valueOf("ASC1"));
    }
}
