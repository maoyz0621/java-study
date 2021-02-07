/**
 * Copyright 2019 Inc.
 **/
package com.myz.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

/**
 * @author maoyz0621 on 19-8-31
 * @version: v1.0
 */
public class MonoTest {

    @Test
    public void test(){
        Mono.just("1").subscribe(System.out::println);
    }
}
