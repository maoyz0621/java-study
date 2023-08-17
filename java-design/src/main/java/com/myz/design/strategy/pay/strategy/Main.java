package com.myz.design.strategy.pay.strategy;

/**
 * @author maoyz
 */
public class Main {

    public static void main(String[] args) {
        PayStrategyContext payStrategyContext = new PayStrategyContext();
        String context = payStrategyContext.generatePayParams(PayTypeEnum.ALIPAY_APP, null);
        System.out.println(context);
    }
}
