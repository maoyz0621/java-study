/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:23  Inc. All rights reserved.
 */
package com.myz.java.study.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author maoyz
 */
public class ToStringUtils {
    private static final String LINE_SEPARATOR = "\n";
    private static final String NULL_TEXT = "<null>";

    public static final ToStringStyle THE_STYLE = new ToStringUtils.SimpleMultiLineToStringStyle();

    public static String toSimpleString(Object obj) {
        String toStringResult = ToStringBuilder.reflectionToString(obj, THE_STYLE);
        String[] split = toStringResult.split(LINE_SEPARATOR);
        StringBuilder result = new StringBuilder();
        String[] var4 = split;
        int var5 = split.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String string = var4[var6];
            if (!string.endsWith(NULL_TEXT)) {
                result.append(string + LINE_SEPARATOR);
            }
        }

        if (result.length() == 0) {
            return "";
        } else if (StringUtils.countMatches(result.toString(), LINE_SEPARATOR) == 2) {
            return result.toString().split("\n")[0] + "<all null values>]";
        } else {
            return result.deleteCharAt(result.length() - 1).toString();
        }
    }

    private static class SimpleMultiLineToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 4645306494220335355L;


        public SimpleMultiLineToStringStyle() {
            this.setContentStart("[");
            this.setFieldSeparator("\n  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd("\n]");
            this.setNullText(NULL_TEXT);
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }
    }
}