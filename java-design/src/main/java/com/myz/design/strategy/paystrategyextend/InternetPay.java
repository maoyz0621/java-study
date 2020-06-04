package com.myz.design.strategy.paystrategyextend;

/**
 * 网络支付
 * @author maoyz on 18-1-6.
 */
public class InternetPay implements PayMethod{

    @Override
    public void pay(double price) {
        System.out.println("网络支付..." + price * 0.9);
    }
}
