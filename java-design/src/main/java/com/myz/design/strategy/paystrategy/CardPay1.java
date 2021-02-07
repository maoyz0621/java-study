package com.myz.design.strategy.paystrategy;

/**
 * 银行卡支付
 *
 * @author maoyz on 18-1-6.
 */
public class CardPay1 implements PayStrategy {

    /**
     * 银行账号
     */
    private String account;

    /**
     * 构造方法
     *
     * @param account 银行账号
     */
    public CardPay1(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    /**
     * 银行支付
     *
     * @param payContext 支付工资的上下文，里面包含算法需要的数据
     */
    @Override
    public void pay(PayContext payContext) {
        System.out.println("现在给:" + payContext.getUsername() + "的银行账号" + this.getAccount() + "支付:" + payContext.getMoney());
    }
}
