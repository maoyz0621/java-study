package com.myz.opensource.apachcommons;

import com.google.common.base.CaseFormat;
import org.junit.Test;

/**
 * 驼峰转下划线功能
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/18 16:09
 */
public class CaseFormatTest {

    @Test
    public void test() {
        // abcD
        System.out.println("- 转驼峰 " + CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "abc-d"));
        // abcD
        System.out.println("_ 转驼峰 " + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "abc_d"));
        // AbcD
        System.out.println("_ 转驼峰 " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "abc_d"));
    }

    @Test
    public void test1() {
        // abcd
        System.out.println("驼峰 转 _ " + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "abcd"));
        // abc_d
        System.out.println("驼峰 转 _ " + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "abcD"));
        // abc-d
        System.out.println("驼峰 转 - " + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "abcD"));
    }

    @Test
    public void test2() {
        String a = "orderColumn";
        // order_column
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, a));
        // OrderColumn
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, a));
    }
}
