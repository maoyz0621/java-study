package com.myz.java.study.design.strategy.paystrategy;

/**
 * @author maoyz on 18-1-6.
 */
public class PayContext {

    /**
     * 员工姓名
     */
    private String username;

    /**
     * 员工工资
     */
    private double money;

    /**
     * 支付工资的方式策略的接口
     */
    private PayStrategy payStrategy;

    public PayContext() {
    }

    /**
     * 构造方法
     *
     * @param username    员工姓名
     * @param money       员工工资
     * @param payStrategy 支付工资的方式策略的接口
     */
    public PayContext(String username, double money, PayStrategy payStrategy) {
        this.username = username;
        this.money = money;
        this.payStrategy = payStrategy;
    }

    /**
     * 支付工资
     */
    public void dePay() {
        // 使用客户希望的支付策略来支付工资
        payStrategy.pay(this);
    }

    public String getUsername() {
        return username;
    }

    public double getMoney() {
        return money;
    }
}
