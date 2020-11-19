package com.myz.opensource.apachcommons;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 并集
 * CollectionUtils.union(set1, set2)
 * <p>
 * 交集
 * CollectionUtils.retainAll(set1, set2)
 * <p>
 * 差集
 * CollectionUtils.subtract(set1, set2)
 *
 * @author maoyz on 18-3-5.
 */
public class SetsCollectionTest {

    public static void main(String[] args) {

        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(4);

        System.out.println("========并集==========");
        // 并集
        Collection<Integer> col = CollectionUtils.union(set1, set2);
        for (Integer temp : col) {
            System.out.print(temp + " ");
        }

        System.out.println("\n=========交集=========");

        // 交集
        //col  = CollectionUtils.intersection(set1, set2);
        col = CollectionUtils.retainAll(set1, set2);
        for (Integer temp : col) {
            System.out.print(temp + " ");
        }

        // 差集
        System.out.println("\n=========差集=========");
        col = CollectionUtils.subtract(set1, set2);
        for (Integer temp : col) {
            System.out.print(temp + " ");
        }
    }
}
