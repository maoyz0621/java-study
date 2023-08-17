package com.myz.design.strategy.pay.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略工厂
 *
 * @author maoyz
 */
class PayStrategyFactory {

    private static Map<Integer, PayStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(PayTypeEnum.ALIPAY_WEB.value(), new AlipayWebStrategy());
        strategyMap.put(PayTypeEnum.ALIPAY_APP.value(), new AlipayAppStrategy());
        strategyMap.put(PayTypeEnum.WECHAT_APP.value(), new WechatPayAppStrategy());
    }

    private PayStrategyFactory() {
    }

    private static class InstanceHolder {
        public static PayStrategyFactory instance = new PayStrategyFactory();
    }

    public static PayStrategyFactory getInstance() {
        return InstanceHolder.instance;
    }

    public PayStrategy creator(Integer type) {
        return strategyMap.get(type);
    }

}
