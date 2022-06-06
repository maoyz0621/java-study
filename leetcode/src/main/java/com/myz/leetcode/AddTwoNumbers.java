/**
 * Copyright 2022 Inc.
 **/
package com.myz.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author maoyz0621 on 2022/4/28
 * @version v1.0
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        // 1 -> 2 -> 4
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        // 1 -> 3 -> 4
        ListNode listNode11 = new ListNode(5);
        ListNode listNode21 = new ListNode(6);
        ListNode listNode31 = new ListNode(4);
        listNode11.next = listNode21;
        listNode21.next = listNode31;

        List<Integer> result = new ArrayList<>();
        ListNode node = new AddTwoNumbers().addTwoNumbers(listNode1, listNode11);
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return null;
    }

    /**
     * 链表结构
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