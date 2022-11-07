/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoyz0621 on 2022/11/5
 * @version v1.0
 */
public class JsonParseArrayTest {

    @Test
    public void testJSONObjectParseObject() {
        List<A> list = new ArrayList<>();
        A a1 = new A();
        a1.setA("a1");
        a1.setB(1);
        list.add(a1);

        A a2 = new A();
        a2.setA("a2");
        a2.setB(2);
        list.add(a2);

        System.out.println(JSONObject.toJSONString(list));

        List<A> list1 = JSONObject.parseObject("[{\"a\":\"a1\",\"b\":1},{\"a\":\"a2\",\"b\":2}]", List.class);
        System.out.println(list1);
    }

    @Test
    public void testJSONObjectParseArray() {
        List<A> list = JSONObject.parseArray("[{\"a\":\"a1\",\"b\":1},{\"a\":\"a2\",\"b\":2}]", A.class);
        System.out.println(list);
    }

    @Test
    public void testJSONArrayParseArray() {
        List<A> list = JSONArray.parseArray("[{\"a\":\"a1\",\"b\":1},{\"a\":\"a2\",\"b\":2}]", A.class);
        System.out.println(list);
    }

    @Test
    public void testJSONParseArray() {
        List<A> list = JSON.parseArray("[{\"a\":\"a1\",\"b\":1},{\"a\":\"a2\",\"b\":2}]", A.class);
        System.out.println(list);
    }

}

class A {

    public A() {
    }

    public A(String a, Integer b) {
        this.a = a;
        this.b = b;
    }

    String a;
    Integer b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("A{");
        sb.append("a='").append(a).append('\'');
        sb.append(", b=").append(b);
        sb.append('}');
        return sb.toString();
    }
}