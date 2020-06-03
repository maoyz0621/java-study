package com.myz.design.strategy.pricestrategy;

/**
 * 策略接口,定义价格算法
 * @author maoyz on 18-1-5.
 */
public interface Strategy {

    /**
     * 策略算法
     */
    double calculatePrice(double price);
}
