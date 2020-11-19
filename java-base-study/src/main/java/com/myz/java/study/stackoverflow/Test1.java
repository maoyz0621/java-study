package com.myz.java.study.stackoverflow;

/**
 * += 操作符实质
 *
 * @author maoyz
 */
public class Test1 {

    /**
     * i += j 等同于 i= (type of i) (i + j)
     * 高精度 -> 低精度 ，精度损失
     */
    public static void main(String[] args) {
        int i = 5;
        long j = 8;

        // 编译不通过
        // i = i + j;
        j = i + j;

        i += j;
        System.out.println(i);
    }
}
