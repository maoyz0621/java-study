package com.myz.design.strategy.paystrategyextend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品
 *
 * @author maoyz on 18-1-6.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    public double getProductPrice() {
        return amount * price;
    }
}
