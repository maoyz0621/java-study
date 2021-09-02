package com.myz.opensource.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myz.opensource.jackson.config.DateToLongStdConverter;
import com.myz.opensource.jackson.config.MySecStdConverter;
import com.myz.opensource.jackson.config.MyStdConverter;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/2 20:03
 */
@Data
public class ConverterBean implements Serializable {
    private static final long serialVersionUID = -2609594062546674646L;

    // 反序列化时 String -> Long
    @JsonDeserialize(converter = MyStdConverter.class)
    private Long len;

    // 序列化时 String -> Long
    @JsonSerialize(converter = MyStdConverter.class)
    private String len1;

    @JsonSerialize(converter = MySecStdConverter.class)
    private String val;

    @JsonDeserialize(converter = DateToLongStdConverter.class)
    private Long time;

    @Test
    public void testJsonSerialize() throws Exception {
        ConverterBean converterBean = new ConverterBean();
        converterBean.setLen(1L);
        converterBean.setLen1("12");
        converterBean.setVal("abc");
        converterBean.setTime(System.currentTimeMillis());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(converterBean));
    }

    @Test
    public void testJsonDeserialize() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        ConverterBean converterBean = objectMapper.readValue("{\"len\":1,\"len1\":12,\"val\":\"abc\",\"time\":\"2021-09-02 11:12:11\"}", ConverterBean.class);
        System.out.println(converterBean);
    }
}
