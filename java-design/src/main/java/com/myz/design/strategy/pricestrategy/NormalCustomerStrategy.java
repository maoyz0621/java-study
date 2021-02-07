package com.myz.design.strategy.pricestrategy;

/**
 * 具体策略,为普通客户计算价格
 * @author maoyz on 18-1-5.
 */
public class NormalCustomerStrategy implements Strategy {

    /**
     * 正常客户计算价格
     * @param price　物品价格
     * @return　消费价格
     */
    @Override
    public double calculatePrice(double price) {
        System.out.println("普通客户...");
        return price;
    }
}
