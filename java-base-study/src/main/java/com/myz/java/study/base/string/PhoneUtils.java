/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.base.string;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author maoyz0621 on 2023/7/20
 * @version v1.0
 */
public class PhoneUtils {
    /**
     * 手机格式
     */
    private static final String regex = "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{4}(\\d{4})";

    private static final Pattern pattern = Pattern.compile(regex);

    /**
     * 替换手机号 151****1234
     *
     * @param originPhone 原明文手机号
     * @return 脱敏手机号
     */
    public static String replaceSensitive(String originPhone) {
        if (StringUtils.isEmpty(originPhone)) {
            return StringUtils.EMPTY;
        }

        Matcher matcher = pattern.matcher(originPhone);
        if (matcher.matches()) {
            // $1 第一个小括号的内容；$2 第二个小括号的内容
            return originPhone.replaceAll(regex, "$1****$2");
        }
        return originPhone;
    }

    public static void main(String[] args) {
        String s = PhoneUtils.replaceSensitive("15110000123");
        System.out.println(s);
    }
}