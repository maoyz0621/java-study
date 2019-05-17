package com.myz.java.study.base.collection.equals;

import com.myz.java.study.base.collection.Emp;
import org.junit.Test;

import java.util.*;

/**
 * 当Emp没有重写equals()和hashcode()时
 * set:2
 * map:null
 * <p>
 * 重写equals()和hashcode()时
 * set:1
 * map:"2"
 *
 * @author maoyz on 18-3-12.
 */
public class EqualsTest {

    @Test
    public void testList() {
        List<Emp> lists = new ArrayList<>();
        lists.add(new Emp(1, "demo"));
        lists.add(new Emp(1, "demo"));
        // 2
        System.out.println(lists.size());

    }

    @Test
    public void testSet() {
        Set<Emp> sets = new HashSet<>();
        sets.add(new Emp(1, "demo"));
        sets.add(new Emp(1, "demo"));
        // 1
        System.out.println(sets.size());

    }

    @Test
    public void testMap() {
        Map<Emp, String> map = new HashMap<>();
        map.put(new Emp(1, "demo"), "1");
        map.put(new Emp(1, "demo"), "2");

        System.out.println(map.get(new Emp(1, "demo")));
        // 1
        System.out.println(map.size());

    }
}
