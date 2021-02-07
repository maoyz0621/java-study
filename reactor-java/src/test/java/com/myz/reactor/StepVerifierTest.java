/**
 * Copyright 2019 Inc.
 **/
package com.myz.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author maoyz0621 on 19-8-31
 * @version: v1.0
 */
public class StepVerifierTest {

    private Flux<Integer> flux1() {
        return Flux.just(1, 2, 3, 4, 5);
    }

    private Mono<Integer> mono1() {
        return Mono.error(new RuntimeException("error mono"));
    }

    @Test
    public void test() {
        // StepVerifier.create(flux1()).expectNext(1, 2, 3).expectComplete().verify();
        StepVerifier.create(flux1()).expectNext(1, 2, 3, 4, 5).expectComplete().verify();

        StepVerifier.create(mono1()).expectErrorMessage("error mono").verify();
    }
}
