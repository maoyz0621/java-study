/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.java8;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

/**
 * @author maoyz0621 on 2022/4/30
 * @version v1.0
 */
public class FuncHelper {

    public static <T> BinaryOperator<T> keepFirst() {
        return (a, b) -> a;
    }

    public static <T> BinaryOperator<T> keepLast() {
        return (a, b) -> b;
    }

    public static <K, V> Collector<V, ?, Map<K, V>> keepFirstMap(Function<? super V, ? extends K> keyMapper) {
        return Collectors.toMap(keyMapper, identity(), keepFirst());
    }

    public static <K, V> Collector<V, ?, Map<K, V>> keepLastMap(Function<? super V, ? extends K> keyMapper) {
        return Collectors.toMap(keyMapper, identity(), keepLast());
    }
}