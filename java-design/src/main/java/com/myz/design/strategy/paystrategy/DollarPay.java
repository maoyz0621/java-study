package com.myz.design.strategy.paystrategy;

/**
 * 美元支付
 *
 * @author maoyz on 18-1-6.
 */
public class DollarPay implements PayStrategy {

    /**
     * 美元支付
     *
     * @param payContext 支付工资的上下文，里面包含算法需要的数据
     */
    @Override
    public void pay(PayContext payContext) {
        System.out.println("现在给:" + payContext.getUsername() + "支付美金:" + payContext.getMoney());
    }
}
