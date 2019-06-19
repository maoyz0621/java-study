package com.myz.java.study.design.strategy.paystrategyextend;

/**
 * 卡支付
 * @author maoyz on 18-1-6.
 */
public abstract class CardPay implements PayMethod {

    /**
     * 卡号
     */
    private String account;


    public CardPay() {
    }

    public CardPay(String account){
        this.account = account;
    }

    @Override
    public final void pay(double price) {
        execPay(price);
        System.out.println("卡支付..." + price* 0.8);
    }

    /**
     * 获取卡类型
     * @return　卡类型
     */
    protected abstract int getType();

    /**
     * 真正执行操作
     * @Param price 价格
     */
    protected abstract void execPay(double price);
}
