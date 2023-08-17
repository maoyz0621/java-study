package com.myz.design.strategy.pay.strategy;

import java.util.Map;

public class WechatPayAppStrategy implements PayStrategy {

    @Override
    public String generatePayParams(PayTypeEnum payTypeEnum, Map<String, Object> params) {
        return "WechatPayApp";
    }
}
