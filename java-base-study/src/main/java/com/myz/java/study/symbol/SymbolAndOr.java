/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.symbol;

import org.junit.Test;

/**
 * 双与和逻辑与的区别：双与当左边出现false时，右边不执行（短路效果），但是逻辑与都会执行
 * 双或和逻辑或的区别：双或当左边出现true时，右边不执行（短路效果），但是逻辑或都会执行
 * 结论：& 优先级高于 |
 *
 * @author maoyz0621 on 2022/6/21
 * @version v1.0
 */
public class SymbolAndOr {

    @Test
    public void a() {
        boolean b = a1() && a2() || a3();
        System.out.println("-----------------");
        System.out.println(b);
    }

    @Test
    public void b() {
        // a1 - false
        // a2 - true
        // a3 - false
        boolean b = a1() || a2() && a3();
        System.out.println("-----------------");
        // false
        System.out.println(b);
    }

    @Test
    public void c() {
        // a2 - true
        boolean b = a2() || a4() && a1();
        System.out.println("-----------------");
        // true
        System.out.println(b);
    }

    @Test
    public void c0() {
        // a2 - true
        boolean b = a2() || (a4() && a1());
        System.out.println("-----------------");
        // true
        System.out.println(b);
    }

    @Test
    public void c1() {
        // a2 - true
        // a4 - true
        // a1 - false
        boolean b = a2() | a4() & a1();
        System.out.println("-----------------");
        // true
        System.out.println(b);
    }

    boolean a1() {
        System.out.println("a1 - false");
        return false;
    }

    boolean a2() {
        System.out.println("a2 - true");
        return true;
    }

    boolean a3() {
        System.out.println("a3 - false");
        return false;
    }

    boolean a4() {
        System.out.println("a4 - true");
        return true;
    }
}