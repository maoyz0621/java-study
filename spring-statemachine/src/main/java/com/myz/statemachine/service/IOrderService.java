/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.service;

import com.myz.statemachine.config.Order;

/**
 * @author maoyz0621 on 2022/2/8
 * @version v1.0
 */
public interface IOrderService {

    Order pay(Long id);

    Order deliver(Long id);

    Order receive(Long id);
}