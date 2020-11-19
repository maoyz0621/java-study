package com.myz.design.strategy.pay.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略工厂
 *
 * @author maoyz
 */
class StrategyFactory {

    private static Map<Integer, PayStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(PayType.ALIPAY_WEB.value(), new AlipayWebStrategy());
        strategyMap.put(PayType.ALIPAY_APP.value(), new AlipayAppStrategy());
        strategyMap.put(PayType.WECHAT_APP.value(), new WechatPayAppStrategy());
    }

    private StrategyFactory() {
    }

    private static class InstanceHolder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return InstanceHolder.instance;
    }

    public PayStrategy creator(Integer type) {
        return strategyMap.get(type);
    }

}
