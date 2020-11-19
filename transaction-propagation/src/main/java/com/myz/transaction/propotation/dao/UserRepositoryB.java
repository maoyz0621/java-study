/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.dao;

import com.myz.transaction.propotation.pojo.UserB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Repository
public interface UserRepositoryB extends JpaRepository<UserB, Serializable> {
}
