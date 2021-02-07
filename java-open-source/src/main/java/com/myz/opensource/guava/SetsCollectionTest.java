package com.myz.opensource.guava;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

/**
 * 集合操作
 * 交集 Sets.intersection(set1, set2)
 * 并集 Sets.union(set1, set2)
 * 差集 Sets.difference(set1, set2)
 *
 * @author maoyz on 18-3-3.
 */
public class SetsCollectionTest {

    @Test
    public void test() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 4, 5, 6, 7);
        Set<Integer> set2 = Sets.newHashSet(1, 3, 4, 6, 7, 8);

        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        System.out.println("交集:" + intersection);

        Sets.SetView<Integer> union = Sets.union(set1, set2);
        System.out.println("并集：" + union);

        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        System.out.println("差集：" + difference);
    }
}
