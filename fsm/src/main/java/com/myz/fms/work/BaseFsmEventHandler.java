/**
 * Copyright 2023 Inc.
 **/
package com.myz.fms.work;

import com.myz.fms.core.AbstractFsmEventHandler;
import com.myz.fms.core.FsmContext;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class BaseFsmEventHandler extends AbstractFsmEventHandler {

    @Override
    protected boolean beforeHandle(FsmContext context) {
        return false;
    }

    @Override
    protected boolean doHandle(FsmContext context) {
        return false;
    }

    @Override
    protected boolean afterHandle(FsmContext context) {
        return false;
    }
}