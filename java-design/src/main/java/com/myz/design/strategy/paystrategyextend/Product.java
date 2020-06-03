package com.myz.design.strategy.paystrategyextend;

/**
 * 商品
 * @author maoyz on 18-1-6.
 */
public class Product {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品数量
     */
    private int amount;

    /**
     * 商品单价
     */
    private double price;

    public Product(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public double getProductPrice(){
        return amount * price ;
    }
}
