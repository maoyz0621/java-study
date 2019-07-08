package com.myz.java.study.optional;

import java.lang.reflect.Method;

class A {
    public void name(String str) {
        System.out.println(str);
    }
}

public class Main {
    /**
     * 作为主程序运行
     */
    public static void main(String[] args) throws Exception {
        A a = new A();
        // 测试反射
        Class<?> cl = a.getClass();
        System.out.println(cl);
        // System.out.println(cl2);
        String str = "111";
        Method method = cl.getDeclaredMethod("name", String.class);
        method.invoke(a, str);
        String str1 = null;
        // java.lang.NullPointerException
        System.out.println(str1.equals(null));
        System.out.println("aaa");
    }

}
