/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.opensource.apachcommons;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.WordUtils;
import org.junit.Test;

/**
 * @author maoyz0621 on 2019/3/25
 * @version: v1.0
 */
public class StringTest {

    /**
     * 包含任意一个：containsAny
     */
    @Test
    public void testcontainsAny() {
        // true
        System.out.println(StringUtils.containsAny("abc", "a", "b"));
        // true
        System.out.println(StringUtils.containsAny("abc", "a", "1"));
        // false
        System.out.println(StringUtils.containsAny("abc", "1"));
    }

    /**
     * 空指针判断  isEmpty()
     */
    @Test
    public void testEmpty() {
        /*
         * 空指针判断
         */
        String str1 = "";
        String str2 = null;
        String str3 = " ";
        //:true
        System.out.println(": " + StringUtils.isEmpty(str1));
        //null:true
        System.out.println("null: " + StringUtils.isEmpty(str2));
        // :false
        System.out.println(" : " + StringUtils.isEmpty(str3));
    }

    /**
     * isBlank()
     */
    @Test
    public void testBlack() {
        String str1 = "";
        String str2 = null;
        String str3 = " ";
        //:true
        System.out.println(": " + StringUtils.isBlank(str1));
        //null:true
        System.out.println("null: " + StringUtils.isBlank(str2));
        // :true
        System.out.println(" : " + StringUtils.isBlank(str3));
    }

    /**
     * 判断是否为数字, 判断该字符串是不是为数字(0~9)组成，如果是，返回true 但该方法不识别有小数点和 请注意
     */
    @Test
    public void testNumber() {
        String str4 = "123";
        String str5 = "12 3";
        String str6 = "123QD#";
        //str4:true
        System.out.println("str4: " + StringUtils.isNumeric(str4));
        //str5:false
        System.out.println("str5: " + StringUtils.isNumeric(str5));
        //str6:false
        System.out.println("str6: " + StringUtils.isNumeric(str6));
    }

    /**
     * 统计子字符串出现的次数
     */
    @Test
    public void testCount() {
        String str7 = "abcdefgfedccfg";
        String str8 = "ac";
        String str9 = "c";
        // count: 0
        System.out.println("count: " + StringUtils.countMatches(str7, str8));
        // count: 3
        System.out.println("count: " + StringUtils.countMatches(str7, str9));
    }

    /**
     * 随机数
     */
    @Test
    public void random() {
        String numeric = RandomStringUtils.randomNumeric(4);
        String alphabetic = RandomStringUtils.randomAlphabetic(6);
        String alphanumeric = RandomStringUtils.randomAlphanumeric(6);
        System.out.println("4位数字随机数: " + numeric);
        System.out.println("6位字母随机数: " + alphabetic);
        System.out.println("6位随机数字和字母: " + alphanumeric);

        String str = "hello world,why are you so happy";
        System.out.println("首字符大写:" + WordUtils.capitalize(str));  //:Hello World,why Are You So Happy
    }

    /**
     * 字符串截取
     */
    @Test
    public void substring() {
        String substringAfter = StringUtils.substringAfter("SELECT * FROM PERSON FROM", "FROM");
        //  PERSON FROM
        System.out.println(substringAfter);
        String substringBefore = StringUtils.substringBefore("SELECT * FROM PERSON FROM", "FROM");
        // SELECT *
        System.out.println(substringBefore);
        String substringBeforeLast = StringUtils.substringBeforeLast("SELECT * FROM PERSON FROM", "FROM");
        // SELECT * FROM PERSON
        System.out.println(substringBeforeLast);
    }

    @Test
    public void test() {
        String[] s1 = new String[]{"1", "2", "3"};
        System.out.println("分割数组:" + StringUtils.join(s1, ","));
        System.out.println("添加某个字符，使其长度等于所设置的:" + StringUtils.rightPad("abc", 6, 'T'));
        System.out.println("添加某个字符，使其长度等于所设置的:" + StringUtils.leftPad("abc", 6, 'T'));
        System.out.println("首字母大写:" + StringUtils.capitalize("abc def, ghi aaa"));
        System.out.println("去掉空格:" + StringUtils.deleteWhitespace("   ab  c  "));
        System.out.println("是否包含该字符:" + StringUtils.contains("abc", "ba"));
        System.out.println("是否包含该字符:" + StringUtils.contains("abc", "ab"));
        System.out.println("表示左边的字符:" + StringUtils.left("abc", 2));
    }

    /**
     * StringEscapeUtils  转义Html
     */
    @Test
    public void escape() {
        String escapeHtml4 = StringEscapeUtils.escapeHtml4("<html>");
        System.out.println(escapeHtml4);
    }
}
