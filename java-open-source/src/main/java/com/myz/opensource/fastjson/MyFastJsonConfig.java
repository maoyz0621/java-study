package com.myz.opensource.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 自定义fastjson序列化器
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/7 17:57
 */
public class MyFastJsonConfig implements ObjectSerializer, ObjectDeserializer {

    @Override
    public void write(JSONSerializer serializer, Object o, Object o1, Type type, int i) throws IOException {
        SerializeWriter out = serializer.out;
        out.write(o + "*");
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        String val = lexer.stringVal();
        if (val.contains("*")) {
            return (T) "123";
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

}
