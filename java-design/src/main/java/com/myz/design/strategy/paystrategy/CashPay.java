package com.myz.design.strategy.paystrategy;

/**
 * 现金支付
 *
 * @author maoyz on 18-1-6.
 */
public class CashPay implements PayStrategy {

    /**
     * 现金支付
     *
     * @param payContext 支付工资的上下文，里面包含算法需要的数据
     */
    @Override
    public void pay(PayContext payContext) {
        System.out.println("现在给:" + payContext.getUsername() + "支付现金:" + payContext.getMoney());
    }
}
