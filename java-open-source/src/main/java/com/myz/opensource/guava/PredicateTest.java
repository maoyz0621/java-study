package com.myz.opensource.guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 函数式编程:实现解耦
 * Predicate　断言
 * Function 函数
 * <p>
 * 使用Lists.newArrayList()创建List
 * <p>
 * 工具:
 * Collections2.filter(Collection<E>, Predicate<>) 过滤器
 * transform(Collection, Function) 类型转换
 * Functions.compose(Function,Function) 组合式函数
 *
 * @author maoyz on 18-3-2.
 */
public class PredicateTest {

    /**
     * Predicates 断言
     */
    @Test
    public void testPredicates() {
        // 使用Lists.newArrayList()创建List
        List<String> list = Lists.newArrayList("aba", "abc", "bcb", "abab", "abcba");

        // Collections2.filter(Collection<E>, Predicate<>)
        Collection<String> filter = Collections2.filter(list, (String input) -> {
            // 业务逻辑
            return (new StringBuilder(input).reverse().toString()).equals(input);
        });

        for (String s : filter) {
            System.out.println(s);
        }
    }

    /**
     * Function函数
     */
    @Test
    public void testFunction() {
        Set<Long> set = Sets.newHashSet();
        set.add(System.currentTimeMillis());
        set.add(999999999L);
        set.add(23423423423L);

        // Collections2.transform(set, new Function)
        Collection<String> transform = Collections2.transform(set, (Long input) -> {
            return new SimpleDateFormat("yyyy-MM-dd").format(input);
        });

        for (String s : transform) {
            System.out.println(s);
        }
    }

    /**
     * 组合函数：Functions.compose(Function, Function);
     */
    @Test
    public void testCompose() {
        List<String> list = Lists.newArrayList("abcd1", "dfgdfgdfg", "sdfsd", "dffdgf");

        // function1
        Function<String, String> f1 = (String input) -> {
            return input.toUpperCase();
        };

        Function<String, String> f2 = (String input) -> {
            return input.length() > 5 ? input.substring(0, 5) : input;
        };

        // 组合函数
        Function<String, String> compose = Functions.compose(f1, f2);

        Collection<String> transform = Collections2.transform(list, compose);

        for (String s : transform) {
            System.out.println(s);
        }
    }
}
