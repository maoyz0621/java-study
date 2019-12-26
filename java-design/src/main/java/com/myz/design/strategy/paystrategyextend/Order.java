package com.myz.design.strategy.paystrategyextend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 订单
 * @author maoyz on 18-1-6.
 */
public class Order {

    private List<Product> list = new ArrayList<Product>();

    /**
     * 添加商品
     * @param product　商品
     */
    public void addProduct(Product product){
        list.add(product);
    }

    /**
     * 移除商品
     * @param product　商品
     */
    public void removeProduct(Product product){
        list.remove(product);
    }

    private double getSumPrice(){
        double payPrice = 0;
        Iterator<Product> iterator = list.iterator();
        while (iterator.hasNext()){
            payPrice = payPrice + iterator.next().getProductPrice();
        }
        return payPrice;
    }

    public void doPay(PayMethod payMethod){
        payMethod.pay(getSumPrice());
    }

}
