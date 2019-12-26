package com.myz.opensource.apachcommons;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.SwitchTransformer;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 类型转换器
 * Transformer<I, O>
 * <p>
 * 工具类
 * CollectionUtils.collect(容器，转换器)
 * <p>
 * SwitchTransformer<String, String>(predicates,transformers,null);
 *
 * @author maoyz on 18-3-4.
 */
public class TransformerTest {

    @Test
    public void testTransform() {
        List<Long> list = new ArrayList<>();
        list.add(234232423L);
        list.add(345345345L);

        Transformer<Long, String> transformer = input -> new SimpleDateFormat("yyyy-MM-dd").format(input);

        // 工具类
        Collection<String> collect = CollectionUtils.collect(list, transformer);

        for (String s : collect) {
            System.out.println(s);
        }
    }

    @Test
    public void test() {
        // Predicate1
        Predicate<String> predicate1 = object -> object.length() > 5;

        // Predicate2
        Predicate<String> predicate2 = object -> object.startsWith("demo");

        Predicate[] predicates = {predicate1, predicate2};

        // Transformer1
        Transformer<String, String> transformer1 = input -> input.substring(2);

        // Transformer2
        Transformer<String, String> transformer2 = input -> input.replace("demo", "");

        Transformer[] transformers = {transformer1, transformer2};
        Transformer<String, String> switchTransformer = new SwitchTransformer<String, String>(predicates, transformers, null);

        ArrayList<String> list = new ArrayList<>();
        list.add("ad");
        list.add("sdfdsdsf");

        Collection<String> collect = CollectionUtils.collect(list, switchTransformer);
        for (String s : collect) {
            System.out.println(s);
        }
    }
}
