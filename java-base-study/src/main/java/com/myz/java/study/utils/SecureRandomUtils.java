/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-19 11:45  Inc. All rights reserved.
 */
package com.myz.java.study.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 在安全性要求较高的应用中，应使用更安全的随机数生成器
 *
 * @author maoyz
 */
public class SecureRandomUtils {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000));
        }

    }
}