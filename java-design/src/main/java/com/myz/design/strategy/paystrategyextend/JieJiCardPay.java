package com.myz.design.strategy.paystrategyextend;

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
        return CardEnum.JieJiCard.getCode();
    }

    @Override
    protected void execPay(double price) {
        System.out.println(CardEnum.JieJiCard.getText() + "支付");
    }
}
