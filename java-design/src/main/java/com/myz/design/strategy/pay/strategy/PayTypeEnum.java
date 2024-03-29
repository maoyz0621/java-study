package com.myz.design.strategy.pay.strategy;

/**
 * @author maoyz
 */
public enum PayTypeEnum {

    ALIPAY_WEB(1, "支付宝网页支付"),
    ALIPAY_WAP(2, "支付宝手机网页支付"),
    UNION_WEB(3, "银联网页支付"),
    UNION_WAP(4, "银联手机网页支付"),
    UNION_APP(5, "银联手机APP支付"),
    ALIPAY_APP(6, "支付宝手机APP支付"),
    PSBC_WEB(7, "邮局网页支付"),
    PSBC_WAP(8, "邮局手机网页支付"),
    CEB_WEB(9, "光大银行网页支付"),
    CEB_WAP(10, "光大银行手机网页支付"),
    WECHAT_APP(11, "微信app支付"),
    CEB_GATEWAY_WEB(12, "光大银行网关支付"),
    CEB_GATEWAY_WAP(13, "光大银行手机支付");

    private final Integer value;
    private final String desc;
    private String name;

    PayTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static PayTypeEnum valueOf(int value) {

        for (PayTypeEnum type : PayTypeEnum.values()) {
            if (type.value() == value) {
                return type;
            }
        }
        return null;
    }

    public Integer value() {
        return value;
    }

    public String desc() {
        return desc;
    }

}
