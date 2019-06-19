package com.myz.java.study.base.collection.list;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * StringTokenizer是Enumeration实现类,将字符串分解为标记
 * <p>
 * boolean hasMoreTokens()：同上
 * String nextToken()：返回从当前位置到下一个分隔符的字符串
 * int countTokens() 返回nextToken方法被调用的次数。如果采用构造函数1和2，返回的就是分隔符数量
 *
 * @author maoyz on 18-3-1.
 */
public class EnumerationTest {

    /**
     *
     */
    @Test
    public void test() {
        String str = " this is demo test ";
        // 取出两端空格
        StringTokenizer token = new StringTokenizer(str);

        while (token.hasMoreTokens()) {
            System.out.println("元素'" + token.nextToken() + "'剩余的标记数:" + token.countTokens());
        }
    }
}
