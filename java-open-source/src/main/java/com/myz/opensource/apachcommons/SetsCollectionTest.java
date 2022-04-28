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

    public int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        int[] val = new int[2];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[i] + nums[j] == target) {
                    val[0] = i;
                    val[1] = j;
                    return val;
                }
            }
        }
        return val;
    }

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
        System.out.println(col);

        System.out.println("\n=========交集=========");

        // 交集
        col = CollectionUtils.intersection(set1, set2);
        System.out.println("intersection= " + col);

        col = CollectionUtils.retainAll(set1, set2);
        System.out.println("retainAll= " + col);

        // 差集
        System.out.println("\n=========差集=========");
        col = CollectionUtils.subtract(set1, set2);
        System.out.println(col);

        System.out.println("================== 相等 ==================");
        Set<Integer> set3 = new HashSet<>();
        set3.add(2);
        set3.add(3);
        set3.add(4);
        System.out.println(CollectionUtils.isEqualCollection(set2, set3));
        System.out.println(CollectionUtils.isEqualCollection(set2, set1));
    }
}
