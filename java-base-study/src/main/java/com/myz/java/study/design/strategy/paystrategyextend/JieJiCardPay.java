package com.myz.java.study.design.strategy.paystrategyextend;

/**
 * 借记卡支付
 * @author maoyz on 18-1-6.
 */
public class JieJiCardPay extends CardPay {

    public JieJiCardPay() {
    }

    public JieJiCardPay(String account) {
        super(account);
    }

    @Override
    protected int getType() {
        return CardEnum.JieJiCard.getIndex();
    }

    @Override
    protected void execPay(double price) {
        System.out.println(CardEnum.JieJiCard.getName() + "支付");
    }
}
