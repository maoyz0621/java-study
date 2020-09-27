/**
 * Copyright 2020 Inc.
 **/
package com.myz.opensource.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 如果定义了默认值，
 * 例如对象，会返回空对象，List返回空数组
 *
 * @author maoyz0621 on 2020/9/27
 * @version v1.0
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoBean implements Serializable {

    private String msg;

    private int code;

    /**
     * 单个bean
     * 有值
     */
    @JsonProperty("table")
    private Table table = new Table();

    /**
     * 没值：
     * "table1":null
     * "table2":{"index":null,"column":null}
     */
    @JsonProperty("table1")
    private Table table1;
    @JsonProperty("table2")
    private Table table2 = new Table();

    /**
     * bean list
     * 有值
     */
    @JsonProperty("context")
    private List<Table> context = new LinkedList<>();

    /**
     * 没值
     * "context1":null
     * "context2":[]
     */
    @JsonProperty("context1")
    private List<Table> context1;

    @JsonProperty("context2")
    private List<Table> context2 = new LinkedList<>();

    @JsonProperty("inner")
    private InnerDemoBean innerDemoBean = new InnerDemoBean();

    /**
     * 定义内部类
     */
    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InnerDemoBean {
        @JsonProperty("username")
        private String userName;

    }
}