/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:20  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.apache.commons.lang3.StringUtils;

/**
 * 脱敏
 *
 * @author maoyz
 */
public class DesensitizeUtils {

    public static String username(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return userName;
        } else {
            String name = StringUtils.left(userName, 1);
            return StringUtils.rightPad(name, userName.length(), "*");
        }
    }

    public static String phone(String phoneNum) {
        if (StringUtils.isEmpty(phoneNum)) {
            return phoneNum;
        } else {
            int length = phoneNum.length();
            return length <= 7 ? phoneNum : StringUtils.left(phoneNum, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(phoneNum, 4), phoneNum.length(), "*"), "***"));
        }
    }


    public static String address(String address) {
        if (StringUtils.isEmpty(address)) {
            return "";
        } else {
            int length = address.length();
            return length <= 6 ? address : StringUtils.left(address, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(address, 3), address.length(), "*"), "***"));
        }
    }
}