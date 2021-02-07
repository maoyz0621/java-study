/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myz.opensource.jackson.config.MyNumberJsonSerializer;
import com.myz.opensource.jackson.config.MyStatusJsonSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 注解@JsonSerialize
 * using：自定义序列化
 * nullsUsing：自定义null序列化默认值
 *
 * @author maoyz0621 on 2020/10/26
 * @version v1.0
 */
@Data
public class JsonSerializerBean {

    /**
     * 空值 显示0.00
     */
    @JsonSerialize(using = MyNumberJsonSerializer.class, nullsUsing = MyNumberJsonSerializer.class)
    private BigDecimal price;

    /**
     * 转义 **
     */
    @JsonSerialize(using = MyNumberJsonSerializer.class)
    private BigDecimal feePrice;

    /**
     * 保留2位小数
     */
    @JsonSerialize(using = MyNumberJsonSerializer.class)
    private BigDecimal preferentialPrice;

    @JsonSerialize(using = MyStatusJsonSerializer.class)
    private int status;

    public static void main(String[] args) throws JsonProcessingException {
        JsonSerializerBean bean = new JsonSerializerBean();
        bean.setPrice(null);
        bean.setFeePrice(new BigDecimal("-0.1"));
        bean.setPreferentialPrice(new BigDecimal("12.3454"));
        bean.setStatus(2);

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        //     @Override
        //     public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //         if (o instanceof BigDecimal){
        //             System.out.println(111111);
        //             jsonGenerator.writeNumber(0.00d);
        //         }
        //     }
        // });
        System.out.println(objectMapper.writeValueAsString(bean));
    }
}