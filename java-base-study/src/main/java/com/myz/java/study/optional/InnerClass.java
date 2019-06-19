package com.myz.java.study.optional;

/**
 * @author maoyz on 18-2-13.
 */
public class InnerClass {

    public static void main(String[] args) {

		Outter.InnerA inA = new Outter().new InnerA();
		inA.fun("inA");

		Outter.InnerC inC = new Outter.InnerC();
		inC.fun("inC");

		new Outter().getD("z");

        String name = "myz";
        System.out.println(name);
    }
}

/**
 * 内部类
 */
class Outter{

    private String name = "myz";
    private int a = 175;

    /**
     * 非静态内部类
     * 1 不能有静态属性，除非声明为final
     */
    class InnerA{

        private String name ="hyr";
        /**
         * 非静态内部类不能有静态属性，除非声明为final
         */
        private final static int b = 12;

        // static  InnerA innerA = new InnerA();

        public void fun(String name) {
            //　此时的this指向InnerA
            System.out.println(this);
            //　实参值
            System.out.println(name);
            //　InnerA.name
            System.out.println(this.name);
            //　Outter.InnerA.name
            System.out.println(Outter.this.name);
        }
    }

    /**
     * 私有静态类
     * 外部无法访问，提供方法接口getB()调用
     */
    private class InnerB{

        private String name ="hyr";

        public void fun(String name) {

            System.out.println(this);
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(Outter.this.name);
        }
    }

    /**
     * 静态内部类
     * 1 可以包含静态成员和非静态成员;
     * 2 可以调用外部类的静态属性和方法，但不能调用普通属性和方法
     */
    static class InnerC{

        private String name ="hyr";
        private static int age = 30;

        public void fun(String name) {
            //此时的this指向InnerC
            System.out.println(this);
            System.out.println(name);
            System.out.println(this.name);
            // System.out.println(demo);
        }
    }

    /**
     * 方法内部类(局部内部类)，不常用
     * 1 不能定义静态属性
     * 2 不能访问定义它的方法内的局部变量
     */
    public void getD(final String name) {

        final int age = 12;
        String aa = "demo";
        // static int c = 12;

        class Inner{

            void fun(){
                System.out.println(this);
                System.out.println(name);
                System.out.println(age);
                System.out.println(aa);
            }
        }
        //　内部类方法调用
        new Inner().fun();
    }

    public void getA(String name) {
        new InnerA().fun(name);
    }

    public void getB(String name) {
        new InnerB().fun(name);
    }

    public void getC(String name) {
        new InnerC().fun(name);
    }
}
