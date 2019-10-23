package com.myz.java.study.design.strategy.paystrategy;

/**
 * 银行卡支付
 *
 * @author maoyz on 18-1-6.
 */
public class CardPay implements PayStrategy {
    /**
     * 银行支付
     *
     * @param payContext 支付工资的上下文，里面包含算法需要的数据
     */
    @Override
    public void pay(PayContext payContext) {
        PayContextExtend payContextExtend = (PayContextExtend) payContext;
        System.out.println("现在给:" + payContextExtend.getUsername() + "的银行账号" + payContextExtend.getAccount() + "支付:" + payContextExtend.getMoney());
    }
}
