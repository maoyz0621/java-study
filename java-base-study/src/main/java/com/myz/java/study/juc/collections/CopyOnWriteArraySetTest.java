package com.myz.java.study.juc.collections;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author maoyz
 */
public class CopyOnWriteArraySetTest {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("a", "a", "1", "1", "2");
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

        for (String s : list) {
            if (!"2".equals(s)) {
                set.add(s);
            }
        }

        System.out.println(set);
    }

}
