package com.myz.java.study.base.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Date;

/**
 * String字符串
 * 字符串常量池里的数据都是相等的
 * String采用Unicode编码,无论中文还是英文,都算一个字符
 * String.valueOf(obj)
 *
 * @author maoyz
 * 2017年6月8日 下午9:07:08
 */
public class StringTest {

    @Test
    public void testsubstring1() {
       String code = "222345";
        System.out.println(code.length());
        String lastCode = code.substring(code.length() - 2);
        String middleCode = code.substring(code.length() - 4, code.length() - 2);
        System.out.println(lastCode);
        System.out.println(middleCode);

    }

    @Test
    public void testsubstring() {
        StringBuilder a = new StringBuilder();
        //System.out.println(a.substring(0, a.length() - 1));
        System.out.println("="+StringUtils.substring(a.toString(), 0, a.length() - 1));

    }

    /**
     * 测试常量池intern()
     */
    @Test
    public void testConstantPool() {
        String str1 = "nihao";
        String str2 = "nihao";
        // 手动入池
        String str3 = new String("nihao").intern();
        System.out.println(str1 == str2);    // true
        System.out.println(str1.equals(str2));    // true
        System.out.println(str2 == str3);    // true
        System.out.println(str2.equals(str3));    // true
    }

    // 长度长度测试 UTF-8 / UTF-16
    @Test
    public void testLength() {
        String str1 = "你好";
        String str2 = "nihao";
        // 2
        System.out.println(str1.length());
        // 5
        System.out.println(str2.length());
        String str3 = "𝄞";
        // 2
        System.out.println(str3.length());
        // 代码点数 -> 1
        System.out.println(str3.codePointCount(0, str3.length()));
        String str4 = "\uD834\uDD1E";
        // 𝄞
        System.out.println(str4);

    }

    /**
     * valueOf()
     * 入参类型：Object 、char[] 、基本数据类型(char boolean int long float double)
     */
    @Test
    public void testValueOf() {
        int num = 12;
        boolean flag = 1 > 2;
        char[] chars = {'a', 'b', 'c'};
        System.out.println(String.valueOf(num));
        System.out.println(String.valueOf(flag));
        System.out.println(String.valueOf(chars));
    }

    /**
     * 去除两端空格 trim()
     */
    @Test
    public void testTrim() {
        String str1 = " ab c ";
        // 6
        System.out.println(str1.length());
        // 4
        System.out.println(str1.trim().length());
        System.out.println(str1);
    }

    @Test
    public void testCharAt() {
        String str1 = "aaaabbbcccddddee";
        for (int i = 0; i < str1.length(); i++) {
            System.out.println("第" + i + "个字母:" + str1.charAt(i));
        }
    }

    /**
     * replace(CharSequence)     支持字符和字符串
     * replaceAll(String)  支持字符串和正则表达式
     */
    @Test
    public void testReplace() {
        final String str = "sdfsd876876";
        System.out.println(str.replace("8", "z"));
        System.out.println(str.replaceAll("8", "z"));
        System.out.println(str.replaceFirst("8", "z"));
    }

    /**
     * format() 格式化
     */
    @Test
    public void testFormat() {
        final String s = "aaaa%s";
        System.out.println(String.format(s, "bbbbbbbb"));
        // %s string
        System.out.println(String.format("aaaaaa%s", 11111));
        // %c char
        System.out.println(String.format("aaaaaa%c", 1111));
        // %b boolean
        System.out.println(String.format("abc=%b", 1 > 2));
        // %d 整数
        System.out.println(String.format("a=%d", 12 / 5));
        // %f float
        System.out.println(String.format("a=%f", 12f / 5));
        // %n 换行符
        System.out.println(String.format("a=%n"));
        System.out.println("=======================");

        // +为正数或者负数添加符号
        System.out.println(String.format("a=%+d", 12 / 5));

        // 05 5位数，不够前面补0
        System.out.println(String.format("a=%05d", 12 / 5));

        //////////////////////////////// 时间 //////////////////////////////////
        // %tc 包括全部日期和时间信息    星期四 九月 05 19:42:48 CST 2019
        System.out.println(String.format("%tc", new Date()));
        // %tf  日期  年-月-日   2019-09-05
        System.out.println(String.format("%tF", new Date()));
        // %tf  日期  月/日/年   09/05/19
        System.out.println(String.format("%tD", new Date()));
    }

}
