/**
 * Copyright 2019 Inc.
 **/
package com.myz.ajax.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 忽略类中不存在的字段  @JsonIgnoreProperties(ignoreUnknown = true, value = {"phone"}), value指定忽略序列化的属性名
 *
 * @author maoyz0621 on 19-6-22
 * @version: v1.0
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true, value = {"phone"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserJson implements Serializable {

    private static final long serialVersionUID = -8838124787476696726L;

    /**
     * 只能接收json字段名称为 "user_name", 转json时, 字段名称也是 "user_name"
     */
    @JsonProperty("user_name")
    private String username;

    @NotNull
    private Integer age;

    @NotEmpty
    private String phone;

    /**
     * 忽略属性值
     */
    @JsonIgnore
    private String password;

    /**
     * 日期时间序列化
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @NotEmpty
    private String postCode;

    private Address address = new Address();

}
