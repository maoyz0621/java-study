/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.optional;

import org.junit.Test;

import java.util.*;

/**
 * @author maoyz on 2018/10/18
 * @version: v1.0
 */
public class OptionalTest {

    /**
     * 为非null的值创建一个Optional
     * of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null
     * 如果传入参数为null，则抛出NullPointerException
     * .of(null) --> java.lang.NullPointerException
     */
    @Test
    public void testOptionalOf() {
        String name = null;
        Optional<String> optional = Optional.of(name);
        // java.lang.NullPointerException
        System.out.println(optional.get());
    }

    @Test
    public void testOptionalOf1() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("a", 1);
        Optional<Map<String, Object>> optional = Optional.of(map);
        System.out.println(optional.get().get("a"));
    }

    /**
     * ofNullable()为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
     */
    @Test
    public void testOptionalOfNullable() {
        Optional<String> empty = Optional.ofNullable(null);

        // java.util.NoSuchElementException: No value present
        System.out.println(empty.get());
    }

    /**
     * isPresent()如果值存在返回true，否则返回false
     */
    @Test
    public void testIsPresent() {
        Optional<String> empty = Optional.ofNullable(null);
        boolean present = empty.isPresent();
        // false
        System.out.println(present);
    }

    /**
     * 如果Optional有值则将其返回，否则抛出NoSuchElementException
     */
    @Test
    public void testGet() {
        Optional<String> optional = Optional.ofNullable("abc");
        if (optional.isPresent()) {
            // abc
            System.out.println(optional.get());
        }

        System.out.println("====================");

        Optional<String> empty = Optional.ofNullable(null);
        if (empty.isPresent()) {
            // java.util.NoSuchElementException: No value present
            System.out.println(empty.get());
        } else {
            System.out.println("error");
        }
    }

    /**
     * ifPresent()如果Optional实例有值则为其调用consumer，否则不做处理
     */
    @Test
    public void testIfPresent() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("a", 1);
        Optional<Map<String, Object>> optional = Optional.ofNullable(map);
        optional.ifPresent((value) -> System.out.println("a的值 = " + value.getOrDefault("a", 11)));
        optional.ifPresent((value) -> System.out.println("b的值 = " + value.getOrDefault("b", 11)));

        System.out.println("\r\n-----------------------------------\r\n");

        Map<String, Object> emptyMap = null;
        Optional<Map<String, Object>> empty = Optional.ofNullable(emptyMap);
        empty.ifPresent((value) -> System.out.println("a的值 = " + value.getOrDefault("a", 11)));
    }

    /**
     * orElse()如果有值则将其返回，否则返回指定的其它值。
     */
    @Test
    public void testOrElse() {
        Optional<String> optional = Optional.ofNullable("abc");
        Optional<String> empty = Optional.ofNullable(null);
        // abc
        System.out.println(optional.orElse("new value"));
        // new value
        System.out.println(empty.orElse("new value"));
    }

    /**
     * orElseGet()与orElse()方法类似，区别在于得到的默认值。
     * orElse方法将传入的字符串作为默认值，
     * orElseGet方法可以接受Supplier接口的实现用来生成默认值
     */
    @Test
    public void testOrElseGet() {
        Optional<String> optional = Optional.ofNullable("abc");
        Optional<String> empty = Optional.ofNullable(null);
        // abc
        System.out.println(optional.orElseGet(() -> "new value"));
        // new value
        System.out.println(empty.orElseGet(() -> "new value"));
    }

    /**
     * orElseThrow()如果有值则将其返回，否则抛出supplier接口创建的异常
     */
    @Test
    public void testOrElseThrow() {
        Optional<String> optional = Optional.ofNullable("abc");
        Optional<String> empty = Optional.ofNullable(null);
        // abc
        System.out.println(optional.orElseThrow(() -> new RuntimeException()));
        // java.lang.RuntimeException
        System.out.println(empty.orElseThrow(() -> new RuntimeException()));
    }

    /**
     * map()如果有值，则对其执行调用mapping函数得到返回值,  return Optional.ofNullable(mapper.apply(value));
     * 如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional
     */
    @Test
    public void testMap() {
        Optional<String> optional = Optional.ofNullable("abc");
        Optional<String> empty = Optional.ofNullable(null);

        // 直接return結果, map()对其进行Optional处理
        Optional<String> optionalMap = optional.map((value) -> value.toUpperCase());
        Optional<String> emptyMap = empty.map((value) -> value.toUpperCase());

        // ABC
        System.out.println(optionalMap.orElseGet(() -> "ccc"));
        // ccc
        System.out.println(emptyMap.orElseGet(() -> "ccc"));
    }

    /**
     * flatMap()
     */
    @Test
    public void testFlatMap() {
        Optional<String> optional = Optional.ofNullable("abc");
        Optional<String> empty = Optional.ofNullable(null);

        // 需要对结果进行Optional.ofNullable()包装
        Optional<String> optional1 = optional.flatMap((value) -> Optional.ofNullable(value.toUpperCase()));
        Optional<String> empty1 = empty.flatMap((value) -> Optional.ofNullable(value.toUpperCase()));

        System.out.println(optional1.orElseGet(() -> "ccc"));
        // ccc
        System.out.println(empty1.orElseGet(() -> "ccc"));


    }

    /**
     * filter()如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional
     */
    @Test
    public void testFilter() {
        List<String> names = Arrays.asList("YanWei", "YanTian");
        for (String name : names) {
            Optional<String> nameLenLessThan7 = Optional.ofNullable(name).filter((value) -> value.length() < 7);

            System.out.println(nameLenLessThan7.orElseGet(() -> "The name is more than 6 characters"));
        }
    }

}
