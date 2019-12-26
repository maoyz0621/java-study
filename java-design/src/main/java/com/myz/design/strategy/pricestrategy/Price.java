package com.myz.design.strategy.pricestrategy;

/**
 * 主要完成计算向客户所报价格的功能
 * @author maoyz on 18-1-5.
 */
public class Price {

    /**
     * 持有一个具体的策略对象
     */
    private Strategy strategy;

    public Price() {
    }

    /**
     * 构造方法中传入一个具体的策略对象
     * @param strategy　策略对象
     */
    public Price(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 报价，计算对客户的报价
     * @param price 商品销售原价
     * @return 计算出来的，应该给客户报的价格
     */
    public double payPrice(double price){
        //通常会转调具体的策略对象进行算法运算
        return strategy.calculatePrice(price);
    }
}
