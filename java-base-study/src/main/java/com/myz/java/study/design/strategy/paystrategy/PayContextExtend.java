package com.myz.java.study.design.strategy.paystrategy;

/**
 * 扩展的支付上下文对象
 * @author maoyz on 18-1-6.
 */
public class PayContextExtend extends PayContext {

    /**
     * 银行账号
     */
    private String account;

    /**
     * 构造方法
     * @param username
     * @param money
     * @param payStrategy
     * @param account 银行账号
     */
    public PayContextExtend(String username, double money,
                            PayStrategy payStrategy, String account) {
        super(username, money, payStrategy);
        this.account = account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
