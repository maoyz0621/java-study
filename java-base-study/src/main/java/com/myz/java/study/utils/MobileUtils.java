/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:30  Inc. All rights reserved.
 */
package com.myz.java.study.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 手机号
 *
 * @author maoyz
 */
public final class MobileUtils {

    static final String TYPE_CHINA_UNICOM = "中国联通";
    static final String TYPE_CHINA_MOBILE = "中国移动";
    static final String TYPE_CHINA_TELECOM = "中国电信";
    static final String TYPE_CHINA_UNKNOWN = "未知";

    public static final String PATERRN_PHONE = "^1[3456789]\\d{9}$";
    public static final String PATERRN_CHINA_MOBILE = "^1(34[0-8]|(3[5-9]|47|5[0127-9]|8[2378]|[49]8)\\d)\\d{7}$";
    public static final String PATERRN_CHINA_UNICOM = "^1(3[0-2]|45|5[56]|8[56]|[46]6)\\d{8}$";
    public static final String PATERRN_CHINA_TELECOM = "^1((33|53|8[09]|99)[0-9]|349)\\d{7}$";

    public static final Pattern PATERRN_CHINA_MOBILE_PATTERN = Pattern.compile(PATERRN_CHINA_MOBILE);
    public static final Pattern PATERRN_CHINA_MOBILE_UNICOM = Pattern.compile(PATERRN_CHINA_UNICOM);
    public static final Pattern PATERRN_CHINA_MOBILE_TELECOM = Pattern.compile(PATERRN_CHINA_TELECOM);


    public static boolean isValid(String mobile) {
        return !StringUtils.isEmpty(mobile) && mobile.matches(PATERRN_PHONE);
    }

    public static String getOperators(String mobile) {
        if (PATERRN_CHINA_MOBILE_PATTERN.matcher(mobile).matches()) {
            return TYPE_CHINA_MOBILE;
        } else if (PATERRN_CHINA_MOBILE_UNICOM.matcher(mobile).matches()) {
            return TYPE_CHINA_UNICOM;
        } else {
            return PATERRN_CHINA_MOBILE_TELECOM.matcher(mobile).matches() ? TYPE_CHINA_TELECOM : TYPE_CHINA_UNKNOWN;
        }
    }

}