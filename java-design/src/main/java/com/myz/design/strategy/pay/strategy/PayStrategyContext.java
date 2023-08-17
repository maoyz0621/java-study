package com.myz.design.strategy.pay.strategy;

import java.util.Map;

/**
 * 支付策略上下文
 * Created by Martin on 2016/7/01.
 */
public class PayStrategyContext {

    /**
     * 调用对应支付平台组装支付请求报文
     *
     * @param payTypeEnum
     * @param params
     * @return
     */
    public String generatePayParams(PayTypeEnum payTypeEnum, Map<String, Object> params) {
        PayStrategy payStrategy = PayStrategyFactory.getInstance().creator(payTypeEnum.value());
        return payStrategy.generatePayParams(payTypeEnum, params);
    }

}
