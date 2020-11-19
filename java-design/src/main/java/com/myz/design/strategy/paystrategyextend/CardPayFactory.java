package com.myz.design.strategy.paystrategyextend;

/**
 * 卡支付方式工厂方法
 * @author maoyz on 18-1-6.
 */
public class CardPayFactory {

    public static CardPay getCardPay(int type){

        switch (type){
            case 1:
                return new JieJiCardPay();
            case 2:
                return new XingYongCardPay();
            default:
                try {
                    throw new Exception("非法支付方式");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
        }
    }

}
