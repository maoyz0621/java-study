package com.myz.design.strategy.paystrategy;

/**
 * @author maoyz on 18-1-6.
 */
public class Main {

    public static void main(String[] args) {
        new PayContext("zhangSan", 5000, new CashPay()).dePay();
        System.out.println("-----------");

        new PayContext("Tom", 3000, new DollarPay()).dePay();
        System.out.println("------------");

        new PayContextExtend("liShi", 6500, new CardPay(), "121212").dePay();
        System.out.println("-----------");

        new PayContext("王五", 7000, new CardPay1("1000000")).dePay();

    }
}
