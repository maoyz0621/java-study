/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Data
@Table(name = "t_user")
@Entity
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    protected UserPO() {
    }

    public UserPO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
