/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.dao;

import com.myz.cache.pojo.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
}
