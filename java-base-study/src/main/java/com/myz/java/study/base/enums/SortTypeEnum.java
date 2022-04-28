/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.base.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author maoyz0621 on 2022/4/28
 * @version v1.0
 */
public enum SortTypeEnum {

    ASC(0, "正序"),

    DESC(1, "倒序"),
    ;

    private final int code;
    private final String desc;
    private final static SortTypeEnum[] values = SortTypeEnum.values();

    /**
     * 默认 private
     * 在JVM加载时一次性完成
     */
    SortTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SortTypeEnum of(String desc) {
        if (StringUtils.isEmpty(desc)) {
            return null;
        }
        return Arrays.stream(values)
                .filter(param -> param.desc.equals(desc))
                .findFirst()
                .orElse(null);
    }

    public static SortTypeEnum fromCode(int code) {
        for (SortTypeEnum value : values) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(of("正序1"));
        System.out.println(of("正序"));
        System.out.println(fromCode(1));
    }
}