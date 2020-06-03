package com.myz.design.strategy.pricestrategy;

/**
 * 客户端进行计算
 * @author maoyz on 18-1-5.
 */
public class Main {

    public static void main(String[] args) {
        // 一般客户
        Strategy normalCustomer = new NormalCustomerStrategy();
        // 老客户
        Strategy oldCustomer = new OldCustomerStrategy();
        // VIP客户
        Strategy vipCustomer = new VipCustomerStrategy();

        double normalPrice = new Price(normalCustomer).payPrice(1000);
        double oldPrice = new Price(oldCustomer).payPrice(1000);
        double vipPrice = new Price(vipCustomer).payPrice(1000);

        System.out.println(normalPrice);
        System.out.println(oldPrice);
        System.out.println(vipPrice);

    }
}
