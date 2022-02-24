/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.service;

import com.myz.statemachine.config.OrderContext;

/**
 * @author maoyz0621 on 2022/2/8
 * @version v1.0
 */
public interface IOrderService {

    OrderContext pay(Long id);

    OrderContext deliver(Long id);

    OrderContext receive(Long id);
}