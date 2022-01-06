/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


/**
 * 计时器
 * @author maoyz0621 on 2021/12/26
 * @version v1.0
 */
@Slf4j
public class StopwatchTest {

    public static void main(String[] args) throws Exception {
        Stopwatch stopWatch = Stopwatch.createStarted();

        TimeUnit.MILLISECONDS.sleep(500);

        // elapsed 用特定的格式返回这个stopwatch经过的时间
        log.info("first = {}", stopWatch.elapsed(TimeUnit.MILLISECONDS));

        // 停止计时
        log.info("this work tasks {}", stopWatch.stop());

        // 重新开始
        stopWatch.start();
        TimeUnit.MILLISECONDS.sleep(600);
        log.info("second = {}", stopWatch.elapsed(TimeUnit.MILLISECONDS));

        // 重置
        stopWatch.reset().start();
        TimeUnit.MILLISECONDS.sleep(700);
        log.info("third = {}", stopWatch.elapsed(TimeUnit.MILLISECONDS));

        log.info("end = {}", stopWatch.stop());
    }
}