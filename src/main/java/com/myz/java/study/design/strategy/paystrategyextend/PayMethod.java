package com.myz.java.study.design.strategy.paystrategyextend;

/**
 * 支付方式接口
 * @author maoyz on 18-1-6.
 */
public interface PayMethod {

    /**
     * 支付方式
     * @param price　支付价格
     */
    void pay(double price);
}
