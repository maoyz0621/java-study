package com.myz.design.strategy.pay.strategy;

/**
 * @author maoyz
 */
public class Main {

    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext();
        String context = strategyContext.generatePayParams(PayType.ALIPAY_APP, null);
        System.out.println(context);
    }
}
