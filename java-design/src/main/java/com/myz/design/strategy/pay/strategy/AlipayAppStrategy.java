package com.myz.design.strategy.pay.strategy;

import java.util.Map;

/**
 * 支付宝app支付（对接移动支付，包含国内、国际）
 */
public class AlipayAppStrategy implements PayStrategy {

    @Override
    public String generatePayParams(PayTypeEnum payTypeEnum, Map<String, Object> params) {
        return "AlipayApp";
    }

}
