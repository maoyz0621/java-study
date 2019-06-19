package com.myz.java.study.base.string;

import org.junit.Test;

/**
 * String字符串
 * 字符串常量池里的数据都是相等的
 * String采用Unicode编码,无论中文还是英文,都算一个字符
 * String.valueOf(obj)
 *
 * @author xuwt
 * 2017年6月8日 下午9:07:08
 */
public class StringDemo {

    public static void main(String[] args) {
//		testConstantPool();
//		testLength();
// 		testValueOf();
//		testStringBuilder();
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

    // 长度长度测试
    @Test
    public void testLength() {
        String str1 = "你好";
        String str2 = "nihao";
        // 2
        System.out.println(str1.length());
        // 5
        System.out.println(str2.length());
    }

    /**
     * valueof
     */
    public static void testValueOf() {
        int num = 12;
        boolean flag = true;
        char[] chars = {'a', 'b', 'c'};
        System.out.println(String.valueOf(num));
        System.out.println(String.valueOf(flag));
        System.out.println(String.valueOf(chars));
    }

    @Test
    public void testTrim() {
        String str1 = " ab c ";
        System.out.println(str1.length());        //6
        System.out.println(str1.trim().length());    //4
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
     * replace()     支持字符和字符串
     * replaceAll()  支持字符串和正则表达式
     */
    @Test
    public void testReplace() {
        String str = "sdfsd876876";
        System.out.println(str.replace("8", "z"));
        System.out.println(str.replaceAll("8", "z"));
        System.out.println(str.replaceFirst("8", "z"));
    }
}
