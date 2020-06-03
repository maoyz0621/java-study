package com.myz.design.strategy.paystrategyextend;

/**
 * @author maoyz on 18-1-6.
 */
public class Main {

    public static void main(String[] args) {
        Order order = new Order();
        order.addProduct(new Product("苹果", 10 , 2.0));
        order.addProduct(new Product("香蕉", 20 , 3.0));

        order.doPay(new CashPay());
        System.out.println("-------------");
        order.doPay(new InternetPay());
        System.out.println("--------------");
        order.doPay(CardPayFactory.getCardPay(2));
    }
}
