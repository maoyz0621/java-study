/**
 * Copyright 2022 Inc.
 **/
package com.myz.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * @author maoyz0621 on 2022/4/26
 * @version v1.0
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] i = {11, 15, 1, 15, 10, 2, 7};
        // int[] sum = twoSum(i, 9);
        int[] sum = twoSumHash(i, 9);
        System.out.println(Arrays.toString(sum));
    }

    // 暴力解法
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] val = new int[2];
        int times = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                System.out.println("执行次数：" + (++times));
                if (nums[i] + nums[j] == target) {
                    val[0] = i;
                    val[1] = j;
                    return val;
                }
            }
        }
        System.out.println("没有结果");
        return val;
    }

    public static int[] twoSumHash(int[] nums, int target) {
        int[] val = new int[2];
        // hash存放（补数，数组下标）
        Map<Integer, Integer> map = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (map.containsKey(nums[i])) {
                val[0] = map.get(nums[i]);
                val[1] = i;
                return val;
            }
            map.put(target - nums[i], i);
        }
        System.out.println("没有结果");
        return val;
    }
}