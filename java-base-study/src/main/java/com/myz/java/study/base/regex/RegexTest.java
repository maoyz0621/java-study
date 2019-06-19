package com.myz.java.study.base.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 捕获组()
 * @author maoyz on 18-3-1.
 */
public class RegexTest {

    /**
     * 捕获组()
     * group(0)表示整个匹配的
     */
    @Test
    public void test(){
        String str = "2018-03-01 23:22:12";
        /**
         * 以左括号为准
         * group(0):(\d{4})-((\d{2})-(\d{2}))
         * group(1):(\d{4})
         * group(2):((\d{2})-(\d{2}))
         * group(3):(\d{2})
         * group(4):(\d{2})
         */
        String regex = "(\\d{4})-((\\d{2})-(\\d{2}))";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        //必须要有这句
        matcher.find();

        // 2018-03-01
        System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
        // 2018
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group(1));
        // 03-01
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group(2));
        // 03
        System.out.printf("\nmatcher.group(3) value:%s", matcher.group(3));
        // 01
        System.out.printf("\nmatcher.group(4) value:%s", matcher.group(4));
    }

    /**
     * 反向引用:对捕获分组()进行引用
     * \nnn
     */
    @Test
    public void test1(){
        String str = "gogo toto goto hahaha";
        String regex = "(\\w{2})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()){
            System.out.println(matcher.group());}

    }

    /**
     * 命名捕获组(?<别名>表达式)
     */
    @Test
    public void test2(){
        String str = "2018-03-01 23:22:12";

        String regex = "(?<year>\\d{4})-(?<date>(?<month>\\d{2})-(?<day>\\d{2}))";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        //必须要有这句
        matcher.find();

        // 2018-03-01
        System.out.printf("\nmatcher.group() value:%s", matcher.group(0));
        // 2018
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group("year"));
        // 03-01
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group("date"));
        // 03
        System.out.printf("\nmatcher.group(3) value:%s", matcher.group("month"));
        // 01
        System.out.printf("\nmatcher.group(4) value:%s", matcher.group("day"));
    }

    /**
     * 非捕获组(?:表达式)，只匹配不捕获
     */
    @Test
    public void test3(){
        String str = "2018-03-01 23:22:12";

        // 理论上有4个捕获组,但是第一组(?:\d{4})不被捕获，但仍需要匹配
        String regex = "(?:\\d{4})-(?<date>(?<month>\\d{2})-(?<day>\\d{2}))";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        //必须要有这句
        matcher.find();

        // 2018-03-01
        System.out.printf("\nmatcher.group() value:%s", matcher.group());
        // 03-01
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group("date"));
        // 03
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group("month"));
        // 01
        System.out.printf("\nmatcher.group(3) value:%s", matcher.group("day"));
    }

}
