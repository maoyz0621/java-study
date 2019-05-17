/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.mapper.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * @author maoyz0621 on 19-4-23
 * @version: v1.0
 */
public class JsonMapperTest {
    /**
     * 序列化对象/集合到Json字符串.
     */
    @Test
    public void toJson() {
        ///////////////////////////  Bean  ////////////////////////////////
        TestBean testBean = new TestBean();
        testBean.setName("A");
        String toJson = JsonMapper.INSTANCE.toJson(testBean);
        // {"name":"A","defaultValue":"hello","nullValue":null,"emptyValue":[]}
        System.out.println(toJson);

        ///////////////////////////  Map  ////////////////////////////////
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("name", "A");
        map.put("age", 2);
        String mapString = JsonMapper.INSTANCE.toJson(map);
        // {"name":"A","age":2}
        System.out.println("Map:" + mapString);

        ///////////////////////////  List<String>  ////////////////////////////////
        List<String> stringList = ListUtil.newArrayList("A", "B", "C");
        String listString = JsonMapper.INSTANCE.toJson(stringList);
        // ["A","B","C"]
        System.out.println("String List:" + listString);

        ///////////////////////////  List<Bean>  ////////////////////////////////
        List<TestBean> beanList = ListUtil.newArrayList(new TestBean("A"), new TestBean("B"));
        String beanListString = JsonMapper.INSTANCE.toJson(beanList);
        // [{"name":"A","defaultValue":"hello","nullValue":null,"emptyValue":[]},{"name":"B","defaultValue":"hello","nullValue":null,"emptyValue":[]}]
        System.out.println("Bean List:" + beanListString);

        ///////////////////////////  Bean[]  ////////////////////////////////
        TestBean[] beanArray = new TestBean[]{new TestBean("A"), new TestBean("B")};
        String beanArrayString = JsonMapper.INSTANCE.toJson(beanArray);
        // [{"name":"A","defaultValue":"hello","nullValue":null,"emptyValue":[]},{"name":"B","defaultValue":"hello","nullValue":null,"emptyValue":[]}]
        System.out.println("Array List:" + beanArrayString);
    }

    /**
     * 从Json字符串反序列化对象/集合.
     */
    @Test
    public void fromJson() {
        ///////////////////////////  Bean  ////////////////////////////////
        String json = "{\"name\":\"A\"}";
        TestBean testBean = JsonMapper.INSTANCE.fromJson(json, TestBean.class);
        System.out.println(testBean);

        ///////////////////////////  Map  ////////////////////////////////
        String mapJson = "{\"name\":\"A\",\"age\":2}";
        Map map = JsonMapper.INSTANCE.fromJson(mapJson, HashMap.class);
        System.out.println(map);

        ///////////////////////////  List<String>  ////////////////////////////////
        String listJson = "[\"A\",\"B\",\"C\"]";
        List<String> list = JsonMapper.INSTANCE.fromJson(listJson, List.class);
        System.out.println(list);

        ///////////////////////////  List<Bean>  ////////////////////////////////
        String listBeanJson = "[{\"name\":\"A\"},{\"name\":\"B\"}]";
        List<TestBean> listBean = JsonMapper.INSTANCE.fromJson(listBeanJson,
                JsonMapper.INSTANCE.buildCollectionType(List.class, TestBean.class));
        System.out.println(listBean);
    }

    /**
     * 测试传入空对象,空字符串,Empty的集合,"null"字符串的结果.
     */
    @Test
    public void nullAndEmpty1() {
        ///////////////////////////////// toJson测试 //////////////////////////////////
        ///////////////////////////  Null Bean  ////////////////////////////////
        TestBean nullBean = null;
        String nullBeanString = JsonMapper.INSTANCE.toJson(nullBean);
        // "null" 字符串
        System.out.println(nullBeanString);
        boolean empty = StringUtils.isEmpty(nullBeanString);
        // false
        System.out.println(empty);

        ///////////////////////////  Empty List  ////////////////////////////////
        List<String> emptyList = Lists.newArrayList();
        String emptyListString = JsonMapper.INSTANCE.toJson(emptyList);
        // []
        System.out.println(emptyListString);
        // false
        System.out.println(StringUtils.isEmpty(emptyListString));
    }

    @Test
    public void nullAndEmpty2() {
        ///////////////////////////////// fromJson测试 //////////////////////////////////
        ///////////////////////////  Null for Bean  ////////////////////////////////
        TestBean nullBeanResult = JsonMapper.INSTANCE.fromJson(null, TestBean.class);
        // null
        System.out.println(nullBeanResult);
        //true
        System.out.println(Objects.isNull(nullBeanResult));

        ///////////////////////////  Null String  ////////////////////////////////
        nullBeanResult = JsonMapper.INSTANCE.fromJson("null", TestBean.class);
        // null
        System.out.println(nullBeanResult);
        //true
        System.out.println(Objects.isNull(nullBeanResult));

        ///////////////////////////  "" ////////////////////////////////
        nullBeanResult = JsonMapper.INSTANCE.fromJson("", TestBean.class);
        // null
        System.out.println(nullBeanResult);
        //true
        System.out.println(Objects.isNull(nullBeanResult));

        ///////////////////////////  {}  ////////////////////////////////
        nullBeanResult = JsonMapper.INSTANCE.fromJson("{}", TestBean.class);
        // name=null, defaultValue=hello, nullValue=null, emptyValue=[]
        System.out.println(nullBeanResult);
        //false
        System.out.println(Objects.isNull(nullBeanResult));
    }

    @Test
    public void nullAndEmpty3() {
        ///////////////////////////////// fromJson测试 //////////////////////////////////
        ///////////////////////////  Null for Bean  ////////////////////////////////
        List nullBeanResult = JsonMapper.INSTANCE.fromJson(null, List.class);
        // null
        System.out.println(nullBeanResult);
        //true
        System.out.println(Objects.isNull(nullBeanResult));

        ///////////////////////////  Null String  ////////////////////////////////
        nullBeanResult = JsonMapper.INSTANCE.fromJson("null", List.class);
        // null
        System.out.println(nullBeanResult);
        //true
        System.out.println(Objects.isNull(nullBeanResult));

        ///////////////////////////  "" ////////////////////////////////
        nullBeanResult = JsonMapper.INSTANCE.fromJson("", List.class);
        // null
        System.out.println(nullBeanResult);
        //true
        System.out.println(Objects.isNull(nullBeanResult));

        ///////////////////////////  {}  ////////////////////////////////
        nullBeanResult = JsonMapper.INSTANCE.fromJson("[]", List.class);
        // []
        System.out.println(nullBeanResult);
        // true
        System.out.println(ListUtil.isEmpty(nullBeanResult));
    }

    /**
     * 测试三种不同的Mapper.
     */
    @Test
    public void threeTypeMappers() {
        ///////////////////////////// defaultMapper() //////////////////////////
        JsonMapper jsonMapper = JsonMapper.defaultMapper();
        TestBean testBean = new TestBean("A");
        // {"name":"A","defaultValue":"hello","nullValue":null,"emptyValue":[]}
        System.out.println(jsonMapper.toJson(testBean));

        ///////////////////////////// nonNullMapper() 非Null的属性 //////////////////////////
        JsonMapper nonNullMapper = JsonMapper.nonNullMapper();
        // {"name":"A","defaultValue":"hello","emptyValue":[]}
        System.out.println(nonNullMapper.toJson(testBean));

        ///////////////////////////// nonEmptyMapper() 非Null且非Empty //////////////////////////
        JsonMapper nonEmptyMapper = JsonMapper.nonEmptyMapper();
        // {"name":"A","defaultValue":"hello"}
        System.out.println(nonEmptyMapper.toJson(testBean));
    }

    /**
     * 更新属性值
     */
    @Test
    public void update() {
        TestBean testBean = new TestBean("A");
        testBean.setDefaultValue("aaaaaa");
        JsonMapper.INSTANCE.update("{\"name\":\"AA\"}", testBean);
        // name=AA, defaultValue=aaaaaa, nullValue=null, emptyValue=[]
        System.out.println(testBean);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestBean {
        private String name;
        private String defaultValue = "hello";
        private String nullValue;
        private List<String> emptyValue = new ArrayList();

        public TestBean(String name) {
            this.name = name;
        }
    }
}
