package com.myz.design.strategy.pricestrategy;

/**
 * 具体策略,为VIP客户计算价格
 * @author maoyz on 18-1-5.
 */
public class VipCustomerStrategy implements Strategy {

    /**
     * VIP客户计算价格
     * @param price　物品价格
     * @return　消费价格
     */
    @Override
    public double calculatePrice(double price) {
        System.out.println("VIP客户...");
        return price * 0.8;
    }
}
