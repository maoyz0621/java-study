package com.myz.design.strategy.paystrategyextend;

/**
 * 信用卡支付
 * @author maoyz on 18-1-6.
 */
public class XingYongCardPay extends CardPay {

    public XingYongCardPay() {
    }

    public XingYongCardPay(String account) {
        super(account);
    }

    @Override
    protected int getType() {
        return CardEnum.XingYongCard.getCode();
    }

    @Override
    protected void execPay(double price) {
        System.out.println(CardEnum.XingYongCard.getText() + "支付...");
    }
}
