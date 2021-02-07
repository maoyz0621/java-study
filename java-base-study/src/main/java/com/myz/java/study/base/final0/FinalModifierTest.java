package com.myz.java.study.base.final0;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * final修饰类，无法子类化，即不能被继承。
 * final修饰类，类中的方法默认都是final 修饰的。
 * final修饰方法，如果从设计（Design）的角度去考虑，如果类之间体现了继承关系，那么final 修饰的方法则不能被子类重写或覆盖。如果没有体现继承关系，就从效率的角度考虑吧，但是请切记：对于Java虚拟机来说编译器在编译期间会自动进行内联优化，这是由编译器决定的，对于我们开发人员来说，我们一定要设计好break-even的平衡，不要滥用final。
 * final修饰局部变量，对于基本类型，值恒定，对于引用类型，引用地址保持不变, 但是引用指向的堆内存是可以改变的
 *
 * @author maoyz
 */
public class FinalModifierTest {

    @Test
    public void test0() {
        final int[] a = {1, 2, 3};
        a[1] = 10;
        System.out.println(Arrays.toString(a)); // [1, 10, 3]


        System.out.println("反射直接改array");
        Array.set(a, 2, 20);
        System.out.println(Arrays.toString(a));     // [1, 10, 20]
    }

    @Test
    public void test() {
        a(1);
        a0(2);
        Map map = new ConcurrentHashMap();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String j = String.valueOf(i);
            map(new HashMap<>(), j);
            System.out.println("=====================");
            map(map, j);
            System.out.println("\r\n");
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间：" + (end - start));
    }

    @Test
    public void test1() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        Map map = new ConcurrentHashMap();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String j = String.valueOf(i);
            fixedThreadPool.execute(() -> {
                map(new HashMap<>(), j);
                System.out.println("=====================");
                map(map, j);
                System.out.println("\r\n");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(10, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间：" + (end - start));

        Thread.sleep(10000);
        System.out.println(map.size());
        while (true) {

        }
    }

    /**
     * 基本类型,final修饰的局部变量
     */
    private void a(final int a) {
        // 不能赋值,编译报错
        // a = 1;
        System.out.println(a);
    }

    /**
     * 基本类型的包装类,final修饰的局部变量
     */
    private void a0(final Integer a) {
        // 不能赋值,编译报错
        // a = 1;
        System.out.println(a);
    }

    /**
     * 引用类型 String
     */
    private void str(final String a) {
        // 不能赋值
        // a = "abc";
        System.out.println(a);
    }

    /**
     * 引用类型 Map
     */
    private void map(final Map<String, Object> a, String args) {
        if (a == null) {
            throw new NullPointerException();
        }
        a.put(args, args);
        System.out.println(a);
    }

}
