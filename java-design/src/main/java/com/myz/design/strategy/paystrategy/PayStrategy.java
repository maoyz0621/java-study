package com.myz.design.strategy.paystrategy;

/**
 * 支付工资的策略的接口，公司有多种支付工资的算法
 * 比如：现金、银行卡、现金加股票、现金加期权、美元支付等等
 *
 * @author maoyz on 18-1-6.
 */
public interface PayStrategy {

    /**
     * 支付工资
     *
     * @param payContext 支付工资的上下文，里面包含算法需要的数据
     */
    void pay(PayContext payContext);
}
