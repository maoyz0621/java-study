/**
 * Copyright 2022 Inc.
 **/
package com.myz.leetcode.array;

/**
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author maoyz0621 on 2022/5/10
 * @version v1.0
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] i = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i1 = new RemoveDuplicates().removeDuplicates(i);
        System.out.println(i1);
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        // 游标
        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            // 不相等
            if (nums[i] != nums[left]) {
                // 游标+1，将此时的值赋给游标所在位置的值
                nums[++left] = nums[i];
            }
        }
        return (left + 1);
    }
}