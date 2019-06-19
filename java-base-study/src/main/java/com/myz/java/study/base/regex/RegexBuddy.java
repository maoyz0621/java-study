package com.myz.java.study.base.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern没有构造方法，通过Pattern.compile(regex)获取；
 * Matcher
 *
 *  matcher.start() 返回匹配到的子字符串在字符串中的索引位置.
    matcher.end()返回匹配到的子字符串的最后一个字符在字符串中的索引位置.
    matcher.group()返回匹配到的子字符串
 * @author maoyz on 18-2-22.
 */
public class RegexBuddy {

    private String str = "123-34345-234-00";
    private String regex = "\\d{3,5}";

    @Test
    public void testMatches(){
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        // 字符串是否与正则表达式相匹配,全部匹配
        System.out.println(matcher.matches());
    }

    @Test
    public void testFind(){
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        // 查找字符串中是否有匹配正则表达式的字符/字符串,部分匹配
        System.out.println(matcher.find());
        // 測试匹配的位置
        System.out.println(matcher.start());
        System.out.println(matcher.end());

        // 重置匹配的位置
        matcher.reset();

         /*
         find:部分匹配，从当前位置開始匹配，找到一个匹配的子串，将移动下次匹配的位置
         */
        System.out.println(matcher.find());/*true*/
        System.out.println(matcher.group() + "---" + matcher.start());/*123---0*/
        System.out.println(matcher.find());/*true*/
        System.out.println(matcher.group() + "---" + matcher.start());/*34345---4*/
    }

    @Test
    public void testLookingAt(){
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        /*lookingAt:部分匹配。总是从第一个字符进行匹配,匹配成功了不再继续匹配。匹配失败了,也不继续匹配。*/
        System.out.println(matcher.lookingAt()); /*true*/
        System.out.println(matcher.group() + "---" + matcher.start()); /*123---0*/

        System.out.println(matcher.lookingAt()); /*true*/
        System.out.println(matcher.group() + "---" + matcher.start()); /*123-0*/
    }
}
