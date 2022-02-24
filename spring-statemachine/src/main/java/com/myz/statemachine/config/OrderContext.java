/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

import com.myz.statemachine.enums.OrderState;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
@Data
@Accessors(chain = true)
public class OrderContext {

    private Long id;
    private OrderState status;
}