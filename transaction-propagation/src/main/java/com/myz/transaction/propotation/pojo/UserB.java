/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Data
@Table(name = "t_user_b")
@Entity
@ToString
public class UserB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public UserB() {
    }

    public UserB(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
