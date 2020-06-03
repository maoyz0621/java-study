package com.myz.design.strategy.paystrategyextend;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 *
 * @author maoyz on 18-1-6.
 */
public class Order {

    private List<Product> list = new ArrayList<>();

    /**
     * 添加商品
     *
     * @param product 　商品
     */
    public void addProduct(Product product) {
        list.add(product);
    }

    /**
     * 移除商品
     *
     * @param product 　商品
     */
    public void removeProduct(Product product) {
        list.remove(product);
    }


    public void doPay(PayMethod payMethod) {
        payMethod.pay(getSumPrice());
    }

    private double getSumPrice() {
        double payPrice = 0;
        for (Product product : list) {
            payPrice = payPrice + product.getProductPrice();
        }
        return payPrice;
    }

}
