package com.myz.java.study.design.strategy.paystrategyextend;

/**
 * 现金支付
 * @author maoyz on 18-1-6.
 */
public class CashPay implements PayMethod {

    /**
     *
     * @param price　支付价格
     */
    @Override
    public void pay(double price) {
        System.out.println("现金支付" + price);
    }
}
