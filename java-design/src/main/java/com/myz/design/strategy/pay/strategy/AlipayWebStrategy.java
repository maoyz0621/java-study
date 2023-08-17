package com.myz.design.strategy.pay.strategy;

import java.util.Map;

/**
 * 支付宝web支付（对接即时到账支付，包含国内、国际）
 */
public class AlipayWebStrategy implements PayStrategy {

    @Override
    public String generatePayParams(PayTypeEnum payTypeEnum, Map<String, Object> params) {
        return "AlipayWeb";
    }


}
