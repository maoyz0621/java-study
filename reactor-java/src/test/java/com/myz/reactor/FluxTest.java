/**
 * Copyright 2019 Inc.
 **/
package com.myz.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * @author maoyz0621 on 19-8-31
 * @version: v1.0
 */
public class FluxTest {

    @Test
    public void test1(){
        Flux.just(1, 2, 3, 4, 5).subscribe(System.out::print);
        System.out.println();
    }
}
