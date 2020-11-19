/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda.supplier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * @author maoyz0621 on 19-8-2
 * @version: v1.0
 */
public class SupplierTest {

    public static void main(String[] args) throws ParseException {
        Supplier<SimpleDateFormat> normalDateFormat = () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 每次调用get()方法时都会调用构造方法创建一个新对象。
        SimpleDateFormat sdf = normalDateFormat.get();
        System.out.println(sdf);
        Date date = sdf.parse("2019-03-29 23:24:05");
        System.out.println(date);

        SimpleDateFormat other = normalDateFormat.get();
        System.out.println(other);

        Supplier<A> a = A::new;
        // @277050dc
        System.out.println(a.get());
        // @5c29bfd
        System.out.println(a.get());
    }

    static class A {
        private String name;

        public A() {
        }

        public A(String name) {
            this.name = name;
        }
    }
}
