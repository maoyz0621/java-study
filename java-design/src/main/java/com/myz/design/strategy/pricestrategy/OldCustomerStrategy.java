package com.myz.design.strategy.pricestrategy;

/**
 * 具体策略,为老客户计算价格
 * @author maoyz on 18-1-5.
 */
public class OldCustomerStrategy implements Strategy {

    /**
     * 老客户计算价格
     * @param price　物品价格
     * @return　消费价格
     */
    @Override
    public double calculatePrice(double price) {
        System.out.println("老客户...");
        return price * 0.9;
    }
}
