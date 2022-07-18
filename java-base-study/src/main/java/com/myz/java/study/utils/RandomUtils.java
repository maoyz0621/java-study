/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:22  Inc. All rights reserved.
 */
package com.myz.java.study.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author maoyz
 */
public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    /**
     * Alphabetic
     */
    public static String randomAlphabetic(final int len) {
        return RandomStringUtils.randomAlphabetic(len);
    }

    /**
     * 含有字母和数字的; 字母与数字并用的n
     */
    public static String randomAlphanumeric(final int len) {
        return RandomStringUtils.randomAlphanumeric(len);
    }

    public static String getString(int len) {
        int count = 0;
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer str = new StringBuffer();
        Random r = new Random();

        while (count < len) {
            int i = Math.abs(r.nextInt(62));
            if (i >= 0 && i < chars.length) {
                str.append(chars[i]);
                ++count;
            }
        }

        return str.toString();
    }

    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (max >= min && n <= len) {
            int[] source = new int[len];

            for (int i = min; i < min + len; source[i - min] = i++) {
            }

            int[] result = new int[n];
            Random rd = new Random();
            int index = 0;
            for (int i = 0; i < result.length; ++i) {
                index = Math.abs(rd.nextInt() % len--);
                result[i] = source[index];
                source[index] = source[len];
            }

            return result;
        } else {
            return null;
        }
    }

    public static String randomAlphaNumeric(int len) {
        char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] c = new char[len];
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < len; ++i) {
            c[i] = ch[random.nextInt(ch.length)];
        }

        return new String(c);
    }
}