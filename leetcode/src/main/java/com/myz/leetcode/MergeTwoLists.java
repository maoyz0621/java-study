/**
 * Copyright 2022 Inc.
 **/
package com.myz.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 考察的是递归思想
 *
 * @author maoyz0621 on 2022/5/2
 * @version v1.0
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        // 1 -> 2 -> 4
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        // 1 -> 3 -> 4
        ListNode listNode11 = new ListNode(1);
        ListNode listNode21 = new ListNode(3);
        ListNode listNode31 = new ListNode(4);
        listNode11.next = listNode21;
        listNode21.next = listNode31;

        List<Integer> result = new ArrayList<>();
        ListNode node = new MergeTwoLists().mergeTwoLists(listNode1, listNode11);
        if (node == null) {
            return;
        }
        result.add(node.val);
        ListNode next = node.next;
        while (next != null) {
            result.add(next.val);
            next = next.next;
        }
        System.out.println(result);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode node = list1.val < list2.val ? list1 : list2;
        node.next = mergeTwoLists(node.next, list1.val >= list2.val ? list1 : list2);
        return node;
    }

    /**
     * 单链表结构
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}