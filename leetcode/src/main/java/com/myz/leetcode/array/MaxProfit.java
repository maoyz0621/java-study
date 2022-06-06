/**
 * Copyright 2022 Inc.
 **/
package com.myz.leetcode.array;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 *
 * @author maoyz0621 on 2022/5/10
 * @version v1.0
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] i = {7, 6, 4, 3, 1};
        int i1 = new MaxProfit().maxProfit(i);
        System.out.println(i1);
    }

    /**
     * 定义dp[i][0]表示第i+1天交易完之后手里没有股票的最大利润，dp[i][1]表示第i+1天交易完之后手里持有股票的最大利润。
     * dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]);
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        return 0;
    }
}