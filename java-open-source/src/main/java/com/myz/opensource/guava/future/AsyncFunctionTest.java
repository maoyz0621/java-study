/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.future;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author maoyz0621 on 2021/12/21
 * @version v1.0
 */
public class AsyncFunctionTest {

    public static void main(String[] args) {
        // Futures.transformAsync()
    }

    static class AsyncFunctionSample implements AsyncFunction<Long,String>{

        @Override
        public ListenableFuture<String> apply(@Nullable Long input) throws Exception {
            return null;
        }
    }
}