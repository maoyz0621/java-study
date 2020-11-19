package com.myz.java.study.base.reference;

import org.junit.Test;

/**
 * @author maoyz
 */
public class StringReferenceTest {

    public static void main(String[] args) {
        // main0();
        main3();
    }

    /**
     * Code:
     * 0: ldc           #3                  // String a     -- ldc：数值常量或String常量从常量池中推送至栈顶
     * 2: astore_0                                          -- astore：将栈顶引用型数值存入（第1个）本地变量
     * 3: ldc           #3                  // String a
     * 5: astore_1
     * 6: new           #4                  // class java/lang/StringBuilder                         -- new：创建一个对象，并且其引用进栈
     * 9: dup                                                                         -- dup：复制栈顶数值，并且复制值进栈
     * 10: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V          -- invokespecial：调用超类构造方法、实例初始化方法、私有方法
     * 13: aload_0                                                                                   -- aload：引用型局部变量入栈
     * 14: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;         -- invokevirtual：调用实例方法
     * 17: aload_1
     * 18: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 21: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     * 24: astore_2
     * 25: return
     */
    public static void main0() {
        String a = "a", b = "a";
        String c = a + b;   // (new StringBuilder()).append(a).append(b).toString();
    }

    /**
     * Code:
     * 0: ldc           #8                  // String a1
     * 2: astore_0
     * 3: new           #9                  // class java/lang/String
     * 6: dup
     * 7: ldc           #8                  // String a1
     * 9: invokespecial #10                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
     * 12: astore_1
     * 13: new           #4                  // class java/lang/StringBuilder
     * 16: dup
     * 17: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
     * 20: aload_0
     * 21: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 24: aload_1
     * 25: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 28: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     * 31: astore_2
     * 32: return
     */
    public static void main1() {
        String a = "a1", b = new String("a1");
        String c = a + b;   // (new StringBuilder()).append(a).append(b).toString();
    }

    /**
     * Code:
     * 0: new           #9                  // class java/lang/String
     * 3: dup
     * 4: ldc           #11                 // String a2
     * 6: invokespecial #10                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
     * 9: astore_0
     * 10: new           #9                  // class java/lang/String
     * 13: dup
     * 14: ldc           #11                 // String a2
     * 16: invokespecial #10                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
     * 19: astore_1
     * 20: new           #4                  // class java/lang/StringBuilder
     * 23: dup
     * 24: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
     * 27: aload_0
     * 28: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 31: aload_1
     * 32: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 35: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     * 38: astore_2
     * 39: return
     */
    public static void main2() {
        String a = new String("a2"), b = new String("a2");
        // String c = a + b;
    }

    /**
     * 有一个东西叫做常量折叠，是一种编译器优化技术。
     * 就是对于 String s1 = "1" + "2"; 编译器会给你优化成 String s1 = "12";在生成的字节码中，根本看不到 "1" "2" 这两个东西。
     */
    public static void main3() {
        String s1 = "a" + "bc";
        String s2 = "ab" + "c";
        System.out.println(s1 == s2);   // true, 其实只创建了一个 "abc" 字符串对象，且位于字符串常量池中。

        String a = "a";
        String bc = "bc";

        // String s2 = new StringBuilder().append(a).append(b).toString();
        String s3 = a + bc;
        System.out.println(s3 == s2);   // false

        final String a0 = "a";
        final String bc0 = "bc";

        String s4 = a0 + bc0;
        System.out.println(s4 == s2);   // true
    }

    /**
     * String 类型  final修饰
     */
    @Test
    public void test() {
        String temp = "1";
        System.out.println("start = " + temp.hashCode());
        reference(temp);
        System.out.println(temp);

        System.out.println("\r\n================================\r\n");

        String temp1 = null;
        reference(temp1);
        System.out.println(temp1);
    }

    private void reference(String temp) {
        temp = "aaa";
        System.out.println("end = " + temp.hashCode());
    }
}
