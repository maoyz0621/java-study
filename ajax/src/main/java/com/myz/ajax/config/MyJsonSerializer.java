package com.myz.ajax.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/8/5 23:40
 */
public class MyJsonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value instanceof String){
            System.out.println("aaaaaaaaaaaaaa");
        }
    }
}
