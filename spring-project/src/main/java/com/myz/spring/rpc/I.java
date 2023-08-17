/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

/**
 * @author maoyz0621 on 2023/7/25
 * @version v1.0
 */
@RpcClient(value = "i")
public interface I {

    String testA(String val);
}