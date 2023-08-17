/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author maoyz0621 on 2023/7/31
 * @version v1.0
 */
@Component
public class Aservice {

    @Autowired
    private H h;

    @Autowired
    private I i;

    public void a(String val) {
        h.testA(val);
    }

    public void b(String val) {
        i.testA(val);
    }
}