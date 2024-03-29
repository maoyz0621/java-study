package com.myz.design.strategy.pay.strategy;

import java.util.Map;

/**
 * 支付策略路由
 */
public interface PayStrategy {

    /**
     * 调用对应支付平台组装支付请求报文
     *
     * @param payTypeEnum 传入需要的支付方式
     * @param params  其他额外需要的参数
     * @return 生成的支付请求
     */
    String generatePayParams(PayTypeEnum payTypeEnum, Map<String, Object> params);

}
