package com.myz.java.study.base.final0;

import java.util.Random;

/**
 * final修饰的属性初始化方法
 * <p>
 * final关键字可以用于成员变量、本地变量、方法以及类。
 * final成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误。
 * 你不能够对final变量再次赋值。
 * 本地变量必须在声明时赋值。
 * 在匿名类中所有变量都必须是final变量。
 * final方法不能被重写。
 * final类不能被继承。
 * 接口中声明的所有变量本身是final的。
 * final和abstract这两个关键字是反相关的，final类就不可能是abstract的。
 * final方法在编译阶段绑定，称为静态绑定(static binding)。
 * 没有在声明时初始化final变量的称为空白final变量(blank final variable)，它们必须在构造器中或者代码块中初始化。
 * 将类、方法、变量声明为final能够提高性能，这样JVM就有机会进行估计，然后优化。
 * 按照Java代码惯例，final变量就是常量，而且通常常量名要大写。
 *
 * @author maoyz
 */
public class FinalAttribute {

    /* 方法1 直接赋值 */
    private final String attrA = "abc";

    /* 方法2 代码块 */
    private final String attrB;

    /* 方法3 构造器,编译时初始化 */
    private final String attrC;

    /* 编译时初始化 */
    private final int attrD = new Random().nextInt();

    {
        attrB = "123";
    }

    public FinalAttribute() {
        this("123");
    }

    public FinalAttribute(String attr) {
        this.attrC = attr;
    }

    public void test() {
        System.out.println(attrA);
        System.out.println(attrB);
        System.out.println(attrC);
        System.out.println(attrD);
    }


    public static void main(String[] args) {
        FinalAttribute attribute = new FinalAttribute("000");
        attribute.test();
    }

}
