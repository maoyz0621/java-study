package com.myz.java.study.base.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author myz
 */
public class StringBuilderDemo {

    /**
     * 数组长度扩容 ((str.length() + 16) * 2 + 2)
     */
    @Test
    public void testConstructor() {
        // 字符数组初始16
        StringBuilder sb1 = new StringBuilder();

        System.out.println(sb1.length()); // 0
        System.out.println(sb1.capacity());  // 16
        // 定义字符数组初始容量30
        StringBuilder sb2 = new StringBuilder(30);
        System.out.println(sb2.length()); // 0
        System.out.println(sb2.capacity()); // 30
        // 字符数组初始16+传入字符串的长度,3+16=19,value[]={"demo","demo","demo"}
        StringBuilder sb3 = new StringBuilder("aaa");
        System.out.println(sb3.length()); // 3
        System.out.println(sb3.capacity()); //19
    }

    /**
     * StringBuilder
     * 使用new创建,返回StringBuilder类
     * append(obj),可以连缀
     * insert(start, obj)插入
     * replace(start, end, str)替换
     * 使用toString()转变为String类型,可以操作字符串
     */
    @Test
    public void testStringBuilder() {
        // 构造方法传入String
        StringBuilder sb1 = new StringBuilder("Hi");
        sb1.append("nihao");
        sb1.append("sb");
        // 连缀
        sb1.append("ha").append("ha");
        // 替换
        sb1.replace(4, 6, "myz1111");
        sb1.append(true);
        sb1.append(123);
        // insert(start, obj)插入
        sb1.insert(1, 'N');
        sb1.append('a');
        System.out.println(sb1.toString().toUpperCase());
        // 删除(int start, int end)
        sb1.delete(3, 3 + 5);
        System.out.println(sb1.toString().toUpperCase());
    }

    /**
     * 将给定字符串倒转
     * 1 String-->StringBuilder
     * 2 调用StringBuilder中reverse()
     * 3 将StringBuilder-->String
     */
    @Test
    public void testReverse() {
        String str1 = "abcdef";
        System.out.println(str1);
        System.out.println(new StringBuilder(str1).reverse().toString());
        System.out.println(str1);
    }

    /**
     * 1 截取字符串最后一个"/"
     * 2 判断字符串是否是回文
     */
    @Test
    public void test() {
        String str = "www://abc.com/myz";
        if (str.lastIndexOf("/") != -1) {
            System.out.println(str.substring(str.lastIndexOf("/")));
        }

        // 判断回文
        String str1 = "aba";
        if (new StringBuilder(str1).reverse().toString().equals(str1)) {
            System.out.println(str1 + "是回文");
        } else {
            System.out.println(str1 + "不是回文");
        }

        //输出所有汉字,50字换行
        System.out.println((char) 12555);
    }

    @Test
    public void testSplit() {
        /**
         * 测试分隔符
         */
        String str = "ab cd ef gg";    //按空格分割
        String[] strArr = str.split("\\s");        //"\\"
        System.out.println(Arrays.toString(strArr));

        /**
         * 按+-分割
         */
        String str1 = "1+2-3+4-5=?";
        System.out.println(Arrays.toString(str1.split("[\\+\\-]")));

        /**
         * 替换数字为*
         */
        String str2 = "a1b2b3b4";
        System.out.println(str2.replaceAll("\\d+", "*"));
    }

    @Test
    public void testEqual() {
        HashSet<StringBuilder> set = new HashSet<>();
        final StringBuilder a = new StringBuilder("aa");
        final StringBuilder b = new StringBuilder("bb");
        set.add(a);
        set.add(b);

        final StringBuilder c = a;
        c.append("c");
        System.out.println(set);    // [aac, bb]
    }
}
